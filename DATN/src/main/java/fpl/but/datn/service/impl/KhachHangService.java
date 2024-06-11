package fpl.but.datn.service.impl;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.KhachHang;
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
public class KhachHangService implements IKhachHangService, IService<KhachHang> {

    @Autowired
    KhachHangRepository khachHangRepository;
    @Override
    public KhachHang getByID(UUID id) {
        return this.khachHangRepository.findById(id).get();
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
                .taiKhoan(request.getTaiKhoan())
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
    public KhachHang update(UUID uuid, KhachHang khachHang) {
        return null;
    }

    @Override
    public KhachHang update(KhachHang request, UUID id) {
        KhachHang khachHang = new KhachHang();
        khachHang.setId(id);
        khachHang.setMa(request.getMa());
        khachHang.setTen(request.getTen());
        khachHang.setTaiKhoan(request.getTaiKhoan());
        khachHang.setEmail(request.getEmail());
        khachHang.setSdt(request.getSdt());
        khachHang.setGioiTinh(request.getGioiTinh());
        khachHang.setNgaySinh(request.getNgaySinh());
        khachHang.setDiaChi(request.getDiaChi());
        khachHang.setNgayTao(new Date());
        khachHang.setNgaySua(new Date());
        khachHang.setTrangThai(request.getTrangThai());

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
        KhachHang khachHang = findById(id);
        khachHang.setTrangThai(Boolean.TRUE);
        khachHangRepository.save(khachHang);

    }

    @Override
    public KhachHang findById(UUID id) {
        return khachHangRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NO_CUSTOMERS_FOUND));
    }

    @Override
    public Page<KhachHang> getAllKhachHangPageable(Pageable pageable) {
        return khachHangRepository.findAll(pageable);
    }


    @Override
    public List<KhachHang> getAll() {
        return this.khachHangRepository.findAll();
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
    public KhachHang findKHByTenDangNhap(String tenDangNhap) {
        return this.khachHangRepository.findKHByTenDangNhap(tenDangNhap);
    }


}
