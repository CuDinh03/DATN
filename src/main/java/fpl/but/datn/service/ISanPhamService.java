package fpl.but.datn.service;

import fpl.but.datn.entity.SanPham;
import fpl.but.datn.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ISanPhamService {
    List<SanPham> getAll();

    SanPham create(SanPham sanPham);

    SanPham update(SanPham sanPham, UUID id);

    void delete(UUID id);

    void open(UUID id);

    SanPham findById(UUID id);

    Page<SanPham> getAllSanPhamPageable(Pageable pageable);

    List<SanPham> getAllSanPhamDangHoatDong();
}
