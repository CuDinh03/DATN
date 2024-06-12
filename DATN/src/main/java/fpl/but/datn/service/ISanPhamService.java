package fpl.but.datn.service;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ISanPhamService {
    List getAll();
    SanPham create(SanPham sanPham);
    SanPham update(SanPham sanPham, UUID id);
    void delete(UUID id);
    void open(UUID id);
    SanPham findById(UUID id);
    Page<SanPham> getAllSanPhamPageable(Pageable pageable);
}
