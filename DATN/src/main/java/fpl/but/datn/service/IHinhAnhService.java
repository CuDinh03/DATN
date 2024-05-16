package fpl.but.datn.service;

import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.entity.HinhAnh;

import java.util.List;
import java.util.UUID;

public interface IHinhAnhService {
    List getAll();
    HinhAnh create(HinhAnh hinhAnh);
    HinhAnh update(HinhAnh hinhAnh, UUID id);
    boolean delete(UUID id);
    HinhAnh findById(UUID id);
}
