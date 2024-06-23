package fpl.but.datn.service.impl;

import fpl.but.datn.entity.*;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IService;
import fpl.but.datn.service.IThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ThanhToanService implements IThanhToanService, IService<ThanhToan> {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private CTSanPhamService ctSanPhamService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private HoaDonGioHangService hoaDonGioHangService;

    @Override
    public ThanhToan getByID(UUID id) {
        return null;
    }

    @Override
    public ThanhToan create(ThanhToan thanhToan) {

        return null;
    }

    @Override
    public ThanhToan update(UUID uuid, ThanhToan thanhToan) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<ThanhToan> getAll() {
        return null;
    }

    @Override
    public Page<ThanhToan> getAllPageable(Pageable pageable) {
        return null;
    }

    public void thanhToanSanPham(HoaDon request,
                                 List<GioHangChiTiet> listGioHangCt) {


        if (request != null) {
            HoaDon hoaDon = hoaDonService.findById(request.getId());
            if (hoaDon != null) {
                hoaDon.setTrangThai(3);
                hoaDon.setTongTien(request.getTongTien());
                hoaDon.setNgaySua(new Date());
                hoaDon.setNgayTao(new Date());
                if (request.getKhachHang() != null) {
                    hoaDon.setKhachHang(request.getKhachHang());
                }
                hoaDonService.update(hoaDon, hoaDon.getId());
                for (GioHangChiTiet ghCt : listGioHangCt) {
                    System.out.println(ghCt);
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                    hoaDonChiTiet.setId(UUID.randomUUID());
                    hoaDonChiTiet.setGiaBan(ghCt.getChiTietSanPham().getGiaBan());
                    hoaDonChiTiet.setSoLuong(ghCt.getSoLuong());
                    hoaDonChiTiet.setNgayTao(new Date());
                    hoaDonChiTiet.setNgaySua(new Date());
                    hoaDonChiTiet.setChiTietSanPham(ghCt.getChiTietSanPham());
                    hoaDonChiTiet.setHoaDon(hoaDon);
                    hoaDonChiTiet.setTrangThai(1);
                    this.hoaDonChiTietService.create(hoaDonChiTiet);
                }

                List<HoaDonChiTiet> hoaDonChiTiets = this.hoaDonChiTietService.getHoaDonChiTietByIdHoaDon(hoaDon.getId());
                for (HoaDonChiTiet hdct : hoaDonChiTiets) {
                    hdct.setTrangThai(1);
                    this.hoaDonChiTietService.update(hdct, hdct.getId());
                }
                GioHangHoaDon gioHangHoaDon = this.hoaDonGioHangService.findByIdHoaDon(hoaDon.getId());
                GioHang gioHang = this.gioHangService.findById(gioHangHoaDon.getGioHang().getId());
                gioHang.setTrangThai(1);
                this.gioHangService.update(gioHang, gioHang.getId());
            }
        }
    }

    @Transactional
    public void thanhToanSanPhamOnline(GioHang requestGh, BigDecimal tongTien, BigDecimal tongTienGiam,
                                       Voucher voucher, String ghiChu, List<GioHangChiTiet> listGioHangCt) {
        System.out.println("============================");
        System.out.println(listGioHangCt);
        if (requestGh == null || requestGh.getId() == null) {
            throw new IllegalArgumentException("GioHang request is invalid");
        }
        if (tongTien == null || tongTienGiam == null) {
            throw new IllegalArgumentException("TongTien or TongTienGiam is invalid");
        }
        if (listGioHangCt == null || listGioHangCt.isEmpty()) {
            throw new IllegalArgumentException("ListGioHangCt is empty or null");
        }

        GioHang gioHang = Optional.ofNullable(this.gioHangService.findById(requestGh.getId()))
                .orElseThrow(() -> new IllegalArgumentException("GioHang not found with id: " + requestGh.getId()));

        HoaDon hoaDon = HoaDon.builder()
                .nguoiDung(null)
                .khachHang(gioHang.getKhachHang())
                .tongTien(tongTien)
                .tongTienGiam(tongTienGiam)
                .voucher(voucher)
                .ghiChu(ghiChu)
                .build();
        System.out.println("===================");
        System.out.println("trc khi luu");
        System.out.println(hoaDon.toString());
        System.out.println("===================");
        HoaDon hoaDon1 = hoaDonService.create(hoaDon);
        hoaDon1.setTrangThai(1);
        HoaDon hoaDon2 = hoaDonService.update(hoaDon1,hoaDon1.getId());
        for (GioHangChiTiet ghCt : listGioHangCt) {
            System.out.println(ghCt.toString());
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setId(UUID.randomUUID());
            hoaDonChiTiet.setGiaBan(ghCt.getChiTietSanPham().getGiaBan());
            hoaDonChiTiet.setSoLuong(ghCt.getSoLuong());
            hoaDonChiTiet.setNgayTao(new Date());
            hoaDonChiTiet.setNgaySua(new Date());
            hoaDonChiTiet.setChiTietSanPham(ghCt.getChiTietSanPham());
            hoaDonChiTiet.setHoaDon(hoaDon2);
            hoaDonChiTiet.setTrangThai(1);
            System.out.println("===================");
            System.out.println("hoa don chi tiet");
            System.out.println(hoaDonChiTiet.toString());
            System.out.println("===================");

            this.hoaDonChiTietService.create(hoaDonChiTiet);
        }
        this.gioHangService.update(gioHang, gioHang.getId());
    }
}

