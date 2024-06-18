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

    public void thanhToanSanPhamOnline(GioHang requestGh, BigDecimal tongTien, BigDecimal tongTienGiam,
                                       Voucher voucher, String ghiChu, List<GioHangChiTiet> listGioHangCt) {
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

        Random random = new Random();
        HoaDon hoaDon = HoaDon.builder()
                .id(UUID.randomUUID())
                .ma("HD" + random.nextInt(1000))
                .nguoiDung(null)
                .khachHang(gioHang.getKhachHang())
                .tongTien(tongTien)
                .tongTienGiam(tongTienGiam)
                .ngayTao(new Date())
                .ngaySua(new Date())
                .voucher(voucher)
                .ghiChu(ghiChu)
                .trangThai(2)
                .build();
        System.out.println("===================");
        System.out.println(hoaDon.toString());
        System.out.println("===================");
        hoaDonService.create(hoaDon);
        System.out.println("===================");
        System.out.println("sau khi luu");
        HoaDon hoaDon1 = hoaDonRepository.findById(hoaDon.getId()).get();
        System.out.println(hoaDon1);
        System.out.println("===================");

        for (GioHangChiTiet ghCt : listGioHangCt) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setId(UUID.randomUUID());
            hoaDonChiTiet.setGiaBan(ghCt.getChiTietSanPham().getGiaBan());
            hoaDonChiTiet.setSoLuong(ghCt.getSoLuong());
            hoaDonChiTiet.setNgayTao(new Date());
            hoaDonChiTiet.setNgaySua(new Date());
            hoaDonChiTiet.setChiTietSanPham(ghCt.getChiTietSanPham());
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setTrangThai(2);
            System.out.println("===================");
            System.out.println(hoaDonChiTiet);
            System.out.println("===================");
            this.hoaDonChiTietService.create(hoaDonChiTiet);
        }


        gioHang.setTrangThai(2);
        this.gioHangService.update(gioHang, gioHang.getId());
    }
}

