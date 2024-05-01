package fpl.but.datn.service.impl;


import fpl.but.datn.dto.request.AuthenticationRequest;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    public TaiKhoan createAccount(TaiKhoan request) {
        TaiKhoan taiKhoan = new TaiKhoan();

        if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap()))
            throw new AppException(ErrorCode.ACCOUNT_EXISTED);

        taiKhoan.setMa(request.getMa());
        taiKhoan.setId(UUID.randomUUID());
        taiKhoan.setTenDangNhap(request.getTenDangNhap());
        taiKhoan.setMatKhau(request.getMatKhau());
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
    boolean authenticate(AuthenticationRequest request){
        var taiKhoan = taiKhoanRepository.findByTenDangNhap(request.getUsername()).orElseThrow(()->  new RuntimeException("khong tim thay tk"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), taiKhoan.getMatKhau());
    }

}
