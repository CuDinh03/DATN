package fpl.but.datn.service;

import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;

import java.util.List;
import java.util.UUID;

public interface IGioHangChiTietService {
    List getAll();
    GioHangChiTiet create(GioHangChiTiet gioHangChiTiet);
    GioHangChiTiet update(GioHangChiTiet gioHangChiTiet, UUID id);
    boolean delete(UUID id);
    GioHangChiTiet findById(UUID id);
}
