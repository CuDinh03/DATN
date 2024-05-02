package fpl.but.datn.service.impl;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import fpl.but.datn.dto.request.AuthenticationRequest;
import fpl.but.datn.dto.response.AuthenticationResponse;
import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.TaiKhoanRepository;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public TaiKhoan createAccount(TaiKhoan request) {
        TaiKhoan taiKhoan = new TaiKhoan();

        if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap()))
            throw new AppException(ErrorCode.ACCOUNT_EXISTED);

        taiKhoan.setMa(request.getMa());
        taiKhoan.setId(UUID.randomUUID());
        taiKhoan.setTenDangNhap(request.getTenDangNhap());
        ChucVu chucVu = chucVuService.getChucVu(UUID.fromString("9239b027-9ff9-47bc-8a10-080a82df45cd"));

        taiKhoan.setIdChucVu(chucVu);
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getMatKhau()));
        taiKhoan.setNgayTao(new Date());
        taiKhoan.setNgaySua(new Date());
        taiKhoan.setTrangThai(request.getTrangThai());

        return taiKhoanRepository.save(taiKhoan);
    }

    public List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanRepository.findAll();
    }

    public TaiKhoan getTaiKhoan(UUID id) {
        return taiKhoanRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
    }

    public TaiKhoan updateTaiKhoan(UUID id, TaiKhoan request) {
        TaiKhoan taiKhoan = getTaiKhoan(id);
        taiKhoan.setMatKhau(request.getMatKhau());
        taiKhoan.setNgaySua(new Date());
        taiKhoan.setTrangThai(request.getTrangThai());

        return taiKhoanRepository.save(taiKhoan);
    }

    public void deleteTaiKhoan(UUID id) {
        taiKhoanRepository.deleteById(id);
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

        var token = generateToken(request.getTenDangNhap());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(String username) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("adminCu")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", "custom")
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

}
