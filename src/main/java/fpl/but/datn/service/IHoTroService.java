package fpl.but.datn.service;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.entity.HoTro;

import java.util.List;
import java.util.UUID;

public interface IHoTroService {
    List getAll();
    HoTro create(HoTro hoTro);
    HoTro update(HoTro hoTro, UUID id);
    boolean delete(UUID id);
    HoTro findById(UUID id);
}
