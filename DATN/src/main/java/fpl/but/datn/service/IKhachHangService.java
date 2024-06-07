package fpl.but.datn.service;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IKhachHangService {

    KhachHang getKhachHangBySdt(String sdt);

    List getAll();
    KhachHang create(KhachHang khachHang);
    KhachHang update(KhachHang khachHang, UUID id);
    void delete(UUID id);
    void open(UUID id);
    KhachHang findById(UUID id);
    Page<KhachHang> getAllKhachHangPageable(Pageable pageable);
    Page<KhachHang> getAllPageable(Pageable pageable);
}
