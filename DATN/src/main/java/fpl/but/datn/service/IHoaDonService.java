package fpl.but.datn.service;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IHoaDonService {
    List getAll();
    DanhMuc create(HoaDon hoaDon);
    HoaDon update(HoaDon hoaDon, UUID id);
    void delete(UUID id);
    void open(UUID id);
    HoaDon findById(UUID id);
    Page<HoaDon> getAllDanhMucPageable(Pageable pageable);
}
