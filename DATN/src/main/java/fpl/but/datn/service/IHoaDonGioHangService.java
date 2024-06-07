package fpl.but.datn.service;

import fpl.but.datn.entity.GioHangHoaDon;

import java.util.List;
import java.util.UUID;

public interface IHoaDonGioHangService {

    List getAll();
    GioHangHoaDon create(GioHangHoaDon hoaDonGioHang);
    GioHangHoaDon update(GioHangHoaDon hoaDonGioHang, UUID id);
    GioHangHoaDon findById(UUID id);

    List<GioHangHoaDon> getAllByIdHoaDon(UUID id);

}
