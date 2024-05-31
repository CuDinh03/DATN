package fpl.but.datn.service;

import fpl.but.datn.controller.HoaDonGioHangController;
import fpl.but.datn.entity.GioHangHoaDon;
import fpl.but.datn.repository.GioHangHoaDonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IHoaDonGioHangService {
    List getAll();
    GioHangHoaDon create(GioHangHoaDon hoaDonGioHang);
    GioHangHoaDon update(GioHangHoaDon hoaDonGioHang, UUID id);
    GioHangHoaDon findById(UUID id);
    List<GioHangHoaDon> getAllByIdHoaDon(UUID id);

}
