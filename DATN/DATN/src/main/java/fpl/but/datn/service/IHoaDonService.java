package fpl.but.datn.service;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IHoaDonService {
    List getAll();
    HoaDon create(HoaDon hoaDon);
    HoaDon update(HoaDon hoaDon, UUID id);
    void delete(UUID id);
    void open(UUID id);
    HoaDon findById(UUID id);
    Page<HoaDon> getAllHoaDonPageable(Pageable pageable);
    Optional<HoaDon> findByMa(String ma);
    boolean xoaCungHoaDon(UUID id);
    HoaDon updateTrangThai(UUID id, Integer trangThai);
    Page<HoaDon> getHoaDonsByTrangThai(Pageable pageable, Integer trangThai);
    List<HoaDon> findHoaDonByKhachHang(UUID idKhachHang);

}
