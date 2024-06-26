package fpl.but.datn.service;
import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IKhachHangService {


    KhachHang create(KhachHang khachHang);

    KhachHang update(KhachHang khachHang, UUID id);

    void delete(UUID id);

    void open(UUID id);

    KhachHang findById(UUID id);

    Page<KhachHang> getAllKhachHangPageable(Pageable pageable);

    Page<KhachHang> getAllPageable(Pageable pageable);

    List<KhachHang> getAll();


    KhachHang add(KhachHang khachHang);

    KhachHang updateKhachHangById(KhachHang khachHang, UUID id);

    KhachHang getKhachHangBySdt(String sdt);

    KhachHang getKhachHangByIdTaiKhoan(UUID idTaiKhoan);

    KhachHang findKHByTenDangNhap(String tenDangNhap);

    KhachHang createWhenTk(TaiKhoan taiKhoan, String mail);

    boolean existsByEmail(String mail);
}
