package fpl.but.datn.service.impl;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import fpl.but.datn.dto.request.AuthenticationRequest;
import fpl.but.datn.dto.response.AuthenticationResponse;
import fpl.but.datn.dto.response.TaiKhoanResponse;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.TaiKhoanRepository;
import fpl.but.datn.service.ITaiKhoanService;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class TaiKhoanService implements ITaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private GioHangService gioHangService;

    @Transactional
    @Override
    public TaiKhoan createAccount(TaiKhoan request) {
        TaiKhoan taiKhoan = new TaiKhoan();

        if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap())){
                        throw new AppException(ErrorCode.ACCOUNT_EXISTED);
        }
        taiKhoan.setMa("TK0" + request.getTenDangNhap());
        taiKhoan.setId(UUID.randomUUID());
        taiKhoan.setTenDangNhap(request.getTenDangNhap());
        ChucVu chucVu = chucVuService.getChucVuByName("CUSTOMER");
        taiKhoan.setChucVu(chucVu);
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getMatKhau()));
        taiKhoan.setNgayTao(new Date());
        taiKhoan.setNgaySua(new Date());
        taiKhoan.setTrangThai(1);
        TaiKhoan taiKhoan1 = taiKhoanRepository.save(taiKhoan);
        KhachHang khachHang = khachHangService.createWhenTk(taiKhoan1);
        Random random = new Random();
        // Tạo giỏ hàng
        GioHang gioHang = new GioHang();
        gioHang.setMa("GH" + random.nextInt(1000));
        gioHang.setNgayTao(new Date());
        gioHang.setNgaySua(new Date());
        gioHang.setKhachHang(khachHang);
        gioHang.setTrangThai(2);

        gioHangService.create(gioHang);

        return taiKhoan1;
    }

    @Override
    public Page<TaiKhoan> getAllTaiKhoanPageable(Pageable pageable) {
        return taiKhoanRepository.findAll(pageable);
    }

    @Override
    public Page<TaiKhoan> findByRoles(String role, Pageable pageable) {
        return taiKhoanRepository.findByTenChucVu(role,pageable);
    }

    @Override
    public Optional<TaiKhoan> findByNguoiDungByTenDangNhap(String tenDangNhap) {
        return taiKhoanRepository.findByNguoiDungByTenDangNhap(tenDangNhap);
    }

    //    //author
    @NonFinal

    @Value("${jwt.signerKey}")
    private String signerKey;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var taiKhoan = taiKhoanRepository.findByTenDangNhap(request.getTenDangNhap()).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getMatKhau(), taiKhoan.getMatKhau());

        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(taiKhoan);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(TaiKhoan taiKhoan) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(taiKhoan.getTenDangNhap())
                .issuer(taiKhoan.getTenDangNhap())
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(taiKhoan))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try {

            jwsObject.sign(new MACSigner(signerKey));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.out.println("cannot create token");
            throw new RuntimeException();
        }

    }

    private String buildScope(TaiKhoan taiKhoan) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (taiKhoan.getChucVu() != null && taiKhoan.getChucVu().getTen() != null) {
            List<String> tenList = Collections.singletonList(taiKhoan.getChucVu().getTen());
            for (String chucVu : tenList) {
                stringJoiner.add(chucVu);
            }
        }
        return stringJoiner.toString();
    }
    public TaiKhoanResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        TaiKhoan byTenDangNhap = taiKhoanRepository.findByTenDangNhap(name).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        TaiKhoanResponse taiKhoanResponse = new TaiKhoanResponse();

        taiKhoanResponse.setUsername(byTenDangNhap.getTenDangNhap());
        taiKhoanResponse.setChucVu(byTenDangNhap.getChucVu().getTen());
        taiKhoanResponse.setId(String.valueOf(byTenDangNhap.getId()));

        return taiKhoanResponse;
    }
    @Override
    public List<TaiKhoan> getAllTk() {
        return taiKhoanRepository.findAll();
    }

    @Override
    public TaiKhoan getByID(UUID id) {
        return taiKhoanRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));    }

    @Override
    public TaiKhoan update(UUID uuid, TaiKhoan request) {
        TaiKhoan taiKhoan = getByID(uuid);
        taiKhoan.setMatKhau(request.getMatKhau());
        taiKhoan.setNgaySua(new Date());
        taiKhoan.setTrangThai(request.getTrangThai());
        taiKhoanRepository.save(taiKhoan);
        TaiKhoan updateTaiKhoan = getByID(uuid);
        if (updateTaiKhoan==null)
            throw new AppException(ErrorCode.UPDATE_FAILED);
        return updateTaiKhoan;
    }
    @Override
    public void delete(UUID id) {
        TaiKhoan taiKhoan = getByID(id);
        taiKhoan.setTrangThai(0);
        taiKhoanRepository.save(taiKhoan);
    }
    public void open(UUID id) {
        TaiKhoan taiKhoan = getByID(id);
        taiKhoan.setTrangThai(1);
        taiKhoanRepository.save(taiKhoan);

    }
}
