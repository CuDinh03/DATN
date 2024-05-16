package fpl.but.datn.service;

import fpl.but.datn.entity.HoTro;
import fpl.but.datn.entity.HoaDon;

import java.util.List;
import java.util.UUID;

public interface IHoaDonService {
    List getAll();
    HoaDon create(HoaDon hoaDon);
    HoaDon update(HoaDon hoaDon, UUID id);
    boolean delete(UUID id);
    HoaDon findById(UUID id);
}
