package fpl.but.datn.service;
import fpl.but.datn.entity.KhachHang;

import java.util.List;
import java.util.UUID;

public interface IKhachHangService {
    List<KhachHang> getAll();

    KhachHang findById(UUID id);

    KhachHang add(KhachHang khachHang);

    KhachHang update(KhachHang khachHang, UUID id);

    Boolean delete(UUID id);
}
