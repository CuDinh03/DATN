package fpl.but.datn.service;

import fpl.but.datn.entity.HoTro;
import fpl.but.datn.entity.HoaDonChiTiet;

import java.util.List;
import java.util.UUID;

public interface IHoaDonChiTietService {
    List getAll();
    HoaDonChiTiet create(HoaDonChiTiet hoaDonChiTiet);
    HoaDonChiTiet update(HoaDonChiTiet hoaDonChiTiet, UUID id);
    boolean delete(UUID id);
    HoaDonChiTiet findById(UUID id);
}
