package fpl.but.datn.service;

import fpl.but.datn.dto.request.ThongTinDatHangDto;
import fpl.but.datn.entity.ThongTinDatHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IThongTinDatHangService {
    List getAll();
    ThongTinDatHang create(ThongTinDatHang thongTinDatHang);
    ThongTinDatHang update(ThongTinDatHang thongTinDatHang, UUID id);
    void delete(UUID id);
    void open(UUID id);
    ThongTinDatHang findById(UUID id);
    Page<ThongTinDatHang> getAllThongTinDatHangPageable(Pageable pageable);

    List<ThongTinDatHang> getAllThongTinDatHangDangHoatDong();

    ThongTinDatHang updateDm(ThongTinDatHang thongTinDatHang, UUID id);

    List<ThongTinDatHang> getThongTinDatHangByIdKhachHang(UUID idKhachHang);
}
