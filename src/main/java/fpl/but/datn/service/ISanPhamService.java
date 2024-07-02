package fpl.but.datn.service;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.SanPham;

import java.util.List;
import java.util.UUID;

public interface ISanPhamService {
    List getAll();
    SanPham create(SanPham sanPham);
    SanPham update(SanPham sanPham, UUID id);
    boolean delete(UUID id);
    SanPham findById(UUID id);
}
