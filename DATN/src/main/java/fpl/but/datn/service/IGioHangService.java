package fpl.but.datn.service;

import fpl.but.datn.entity.GioHang;

import java.util.List;
import java.util.UUID;

public interface IGioHangService {
    List getAll();
    GioHang create(GioHang gioHang);
    GioHang update(GioHang gioHang, UUID id);
    boolean delete(UUID id);
    GioHang findById(UUID id);
    GioHang findByIdKhachHang(UUID id);
}
