package fpl.but.datn.service.impl;

import fpl.but.datn.dto.response.TaiKhoanResponse;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.repository.TaiKhoanRepository;
import fpl.but.datn.service.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class TaiKhoanService implements ITaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Transactional
    @Override
    public TaiKhoan createAccount(TaiKhoan request, String mail) {
        TaiKhoan taiKhoan = new TaiKhoan();

        if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap())){
                        throw new AppException(ErrorCode.ACCOUNT_EXISTED);
        }
        if (khachHangService.existsByEmail(mail)){
            throw new AppException(ErrorCode.KH_EXISTED_MAIL);
        }
        taiKhoan.setMa("TK0" + request.getTenDangNhap());
        taiKhoan.setId(UUID.randomUUID());
        taiKhoan.setTenDangNhap(request.getTenDangNhap());
        ChucVu chucVu = chucVuRepository.getReferenceById("CUSTOMER");
        var roles = new HashSet<ChucVu>();
        roles.add(chucVu);
        taiKhoan.setChucVus(roles);
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getMatKhau()));
        taiKhoan.setNgayTao(new Date());
        taiKhoan.setNgaySua(new Date());
        taiKhoan.setTrangThai(1);
        TaiKhoan taiKhoan1 = taiKhoanRepository.save(taiKhoan);
        KhachHang khachHang = khachHangService.createWhenTk(taiKhoan1, mail);
        Random random = new Random();
        // Tạo giỏ hàng
        GioHang gioHang = new GioHang();
        gioHang.setMa("GH" + random.nextInt(1000));
        gioHang.setNgayTao(new Date());
        gioHang.setNgaySua(new Date());
        gioHang.setKhachHang(khachHang);
        gioHang.setTrangThai(2);

        gioHangService.create(gioHang);

        emailSenderService.sendAccountCreationEmail(mail, taiKhoan1.getTenDangNhap());


        return taiKhoan1;
    }

    @Override
    public Page<TaiKhoan> getAllTaiKhoanPageable(Pageable pageable) {
        return taiKhoanRepository.findAll(pageable);
    }

    @Override
    public Page<TaiKhoan> findByRoles(String role, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<TaiKhoan> findByNguoiDungByTenDangNhap(String tenDangNhap) {
        return taiKhoanRepository.findByNguoiDungByTenDangNhap(tenDangNhap);
    }

    @Override
    public Optional<TaiKhoan> findByTenDangNhap(String tenDangNhap) {
        return taiKhoanRepository.findByTenDangNhap(tenDangNhap);
    }


    public TaiKhoanResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        TaiKhoan byTenDangNhap = taiKhoanRepository.findByTenDangNhap(name).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        TaiKhoanResponse taiKhoanResponse = new TaiKhoanResponse();

        taiKhoanResponse.setUsername(byTenDangNhap.getTenDangNhap());
        taiKhoanResponse.setChucVu(byTenDangNhap.getChucVus().toString());
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
