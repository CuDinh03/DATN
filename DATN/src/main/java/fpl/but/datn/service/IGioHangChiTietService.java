package fpl.but.datn.service;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IGioHangChiTietService {
    List getAll();
    GioHangChiTiet create(GioHangChiTiet gioHangChiTiet);
    GioHangChiTiet update(GioHangChiTiet gioHangChiTiet, UUID id);
    boolean delete(UUID id);
    void open(UUID id);
    GioHangChiTiet findById(UUID id);
    Page<GioHangChiTiet> getAllGHCTPageable(Pageable pageable);
}
