package fpl.but.datn.service;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;

import java.util.List;
import java.util.UUID;

public interface ICTSanPhamService {
    List getAll();
    ChiTietSanPham create(ChiTietSanPham chiTietSanPham);
    ChiTietSanPham update(ChiTietSanPham chiTietSanPham, UUID id);
    boolean delete(UUID id);
    ChiTietSanPham findById(UUID id);
}