package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.KhachHangRepository;
import fpl.but.datn.service.IKhachHangService;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KhachHangService implements IService<KhachHang>, IKhachHangService {

    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public KhachHang getByID(UUID id) {
        return khachHangRepository.findById(id).get();
    }

    @Override
    @Transactional
    public KhachHang create(KhachHang request) {
        Optional<KhachHang> existingKhachHang = khachHangRepository.getKhachHangBySdt(request.getSdt());

        if (existingKhachHang.isPresent()) {
            throw new AppException(ErrorCode.SDT_ALREADY_USED);
        }

        KhachHang khachHang = KhachHang.builder()
                .ma("KH" + System.currentTimeMillis())
                .ten(request.getTen())
                .email(request.getEmail())
                .sdt(request.getSdt())
                .gioiTinh(request.getGioiTinh())
                .ngaySinh(request.getNgaySinh())
                .diaChi(request.getDiaChi())
                .ngayTao(new Date())
                .ngaySua(new Date())
                .trangThai(1)
                .build();
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang update(KhachHang khachHang, UUID id) {
        return null;
    }

    @Override
    public KhachHang update(UUID id, KhachHang request) {
        KhachHang khachHang = KhachHang.builder()
                .id(id)
                .ma(request.getMa())
                .ten(request.getTen())
                .email(request.getEmail())
                .sdt(request.getSdt())
                .gioiTinh(request.getGioiTinh())
                .ngaySinh(request.getNgaySinh())
                .diaChi(request.getDiaChi())
                .ngayTao(request.getNgayTao())
                .ngaySua(new Date())
                .trangThai(1)
                .build();
        return khachHangRepository.save(khachHang);
    }

    @Override
    public void delete(UUID id) {
        KhachHang khachHang = this.khachHangRepository.findById(id).get();
        khachHang.setTrangThai(0);
        this.khachHangRepository.saveAndFlush(khachHang);
    }

    @Override
    public void open(UUID id) {

    }

    @Override
    public List<KhachHang> getAll() {
        return this.khachHangRepository.findAll();
    }

    @Override
    public KhachHang findById(UUID id) {
        return khachHangRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }

    @Override
    public Page<KhachHang> getAllKhachHangPageable(Pageable pageable) {
        return null;
    }

    @Override
    public KhachHang add(KhachHang khachHang) {
        return null;
    }

    @Override
    public KhachHang updateKhachHangById(KhachHang request, UUID id) {
        KhachHang khachHang = KhachHang.builder()
                .id(id)
                .ma(request.getMa())
                .ten(request.getTen())
                .email(request.getEmail())
                .sdt(request.getSdt())
                .gioiTinh(request.getGioiTinh())
                .ngaySinh(request.getNgaySinh())
                .diaChi(request.getDiaChi())
                .ngayTao(request.getNgayTao())
                .ngaySua(new Date())
                .trangThai(1)
                .taiKhoan(request.getTaiKhoan())
                .build();
        return khachHangRepository.save(khachHang);
    }

    @Override
    public Page<KhachHang> getAllPageable(Pageable pageable) {
        return this.khachHangRepository.findAllPage(pageable);
    }

    @Override
    public KhachHang getKhachHangBySdt(String sdt) {
        return this.khachHangRepository.getKhachHangBySdt(sdt).get();
    }

    @Override
    public KhachHang getKhachHangByIdTaiKhoan(UUID idTaiKhoan) {
        return khachHangRepository.getKhachHangByIdTaiKhoan(idTaiKhoan).get();
    }

    @Override
    public KhachHang findKHByTenDangNhap(String tenDangNhap) {
        return this.khachHangRepository.findKHByTenDangNhap(tenDangNhap);
    }

}