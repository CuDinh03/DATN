package fpl.but.datn.service;

import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IHoaDonChiTietService {
    List getAll();
    HoaDonChiTiet create(HoaDonChiTiet hoaDonChiTiet);
    HoaDonChiTiet update(HoaDonChiTiet hoaDonChiTiet, UUID id);
    boolean delete(UUID id);
    void open(UUID id);
    HoaDonChiTiet findById(UUID id);
    Page<HoaDonChiTiet> getAllDanhMucPageable(Pageable pageable);
    List<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(UUID idHoaDon);
    List<Object[]> findAllChiTietAndHinhAnhByIdHoaDon(UUID idGioHang);
    List<HoaDonChiTietDto> thongKeSanPhamBanNhieuNhat();
    List<HoaDonChiTiet> updateHoaDonChiTiet(List<HoaDonChiTiet> chiTietList);

}
