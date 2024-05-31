package fpl.but.datn.service;

import fpl.but.datn.entity.GioHangChiTiet;

import java.util.List;
import java.util.UUID;

public interface IGioHangChiTietService {
    List getAll();
    GioHangChiTiet create(GioHangChiTiet hoaDonGioHang);
    GioHangChiTiet update(GioHangChiTiet hoaDonGioHang, UUID id);
    GioHangChiTiet findById(UUID id);
    List<GioHangChiTiet> getAllByIdGioHang(UUID id);
}
