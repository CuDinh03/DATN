package fpl.but.datn.service.impl;

import fpl.but.datn.entity.*;
import fpl.but.datn.service.IService;
import fpl.but.datn.service.IThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ThanhToanService implements IThanhToanService, IService<ThanhToan> {

    @Autowired
    private HoaDonService hoaDonService;

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
                hoaDon.setTrangThai(Boolean.FALSE);
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
                    hoaDonChiTiet.setTrangThai(Boolean.TRUE);
                    this.hoaDonChiTietService.create(hoaDonChiTiet);
                }

                List<HoaDonChiTiet> hoaDonChiTiets = this.hoaDonChiTietService.getHoaDonChiTietByIdHoaDon(hoaDon.getId());
                for (HoaDonChiTiet hdct : hoaDonChiTiets) {
                    hdct.setTrangThai(Boolean.TRUE);
                    this.hoaDonChiTietService.update(hdct, hdct.getId());
                }
                GioHangHoaDon gioHangHoaDon = this.hoaDonGioHangService.findByIdHoaDon(hoaDon.getId());
                GioHang gioHang = this.gioHangService.findById(gioHangHoaDon.getGioHang().getId());
                gioHang.setTrangThai(Boolean.FALSE);
                this.gioHangService.update(gioHang, gioHang.getId());
            }
        }
    }
}