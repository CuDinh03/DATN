package fpl.but.datn.service;

import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.dto.response.MonthlySalesData;
import fpl.but.datn.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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

    HoaDon updateTrangThai(UUID id, Integer trangThai, String ghiChu);
    Page<HoaDon> getHoaDonsByTrangThai(Pageable pageable, Integer trangThai);
    List<HoaDon> findHoaDonByKhachHang(UUID idKhachHang);
    List<HoaDon> getHoaDonsByTrangThaiAndKhachHang(Integer trangThai, UUID khachHangId);
    Optional<HoaDon> findByMaKH(String ma);
    List<HoaDon> getHoaDonBetweenDates(Date startDate, Date endDate);
    Optional<HoaDon> findByMaAndKhachHang(String ma, UUID khachHangId);
     BigDecimal tinhTongDoanhThu();
    int tinhTongSoLuongSanPham();
     Map<LocalDate, BigDecimal> thongKeDoanhThuTheoNgay();
    Map<LocalDate, Integer> thongKeSoLuongTheoNgay();
    Map<Integer, BigDecimal> thongKeDoanhThuTheoTuan();
    Map<Integer, Integer> thongKeSoLuongTheoTuan();
    Map<Integer, BigDecimal> thongKeDoanhThuTheoThang();
    Map<Integer, Integer> thongKeSoLuongTheoThang();
    BigDecimal tinhPhanTramTangTruongDoanhThu(int namNay);
    HoaDon yeuCauSuaHoaDon(HoaDon request, UUID id);
    boolean canUpdateTrangThai(int currentTrangThai, int newTrangThai, String ghiChu);

    HoaDon updateHoaDon(List<HoaDonChiTietDto> chiTietList, HoaDon hoaDon, NguoiDung nguoiDung);
    void huyDonDaXuLy(HoaDon hoaDon, int trangThai);

    List<MonthlySalesData> findMonthlySalesData();


}
   