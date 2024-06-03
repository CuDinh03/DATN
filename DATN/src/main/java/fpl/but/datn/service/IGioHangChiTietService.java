package fpl.but.datn.service;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.GioHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IGioHangChiTietService {
    List getAll();
    GioHangChiTiet create(GioHangChiTiet gioHangChiTiet);
    GioHangChiTiet update(GioHangChiTiet gioHangChiTiet, UUID id);
    GioHangChiTiet findById(UUID id);
    List<GioHangChiTiet> getAllByIdGioHang(UUID id);
    GioHangChiTiet updateGioHangChiTiet(UUID id, Integer newSoLuong);
}
