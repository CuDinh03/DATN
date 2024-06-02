package fpl.but.datn.service.impl;

import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.GioHangHoaDon;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangHoaDonRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IHoaDonGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@Service
public class HoaDonGioHangService implements IHoaDonGioHangService {

    @Autowired
    private GioHangHoaDonRepository gioHangHoaDonRepository;
    @Autowired
    private GioHangRepository gioHangRepository;
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public List getAll() {
        return gioHangHoaDonRepository.findAllByNgayTao();
    }

    @Override
    public GioHangHoaDon create(GioHangHoaDon hoaDonGioHang) {
        Random random = new Random();
        // Tạo giỏ hàng
        GioHang gioHang = new GioHang();
        gioHang.setMa("GH" + random.nextInt(1000));
        gioHang.setNgayTao(new Date());
        gioHang.setNgaySua(new Date());
        gioHang.setTrangThai(true);
        gioHangRepository.save(gioHang);

        BigDecimal tongTien = BigDecimal.ZERO;
        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietRepository.findAllByIdGioHang(gioHang.getId());
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            tongTien = tongTien.add(gioHangChiTiet.getIdSanPham().getGiaBan().multiply(new BigDecimal(gioHangChiTiet.getSoLuong())));
        }

        // Tạo hóa đơn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa("HD" + random.nextInt(1000));
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        hoaDon.setTrangThai(true);
        hoaDon.setTongTien(tongTien);
        hoaDonRepository.save(hoaDon);

        // Tạo giỏ hàng chi tiết
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setIdGioHang(gioHang);
        gioHangChiTiet.setNgayTao(new Date());
        gioHangChiTiet.setNgaySua(new Date());
        gioHangChiTiet.setTrangThai(true);
        gioHangChiTietRepository.save(gioHangChiTiet);

        // Tạo giỏ hàng hóa đơn
        GioHangHoaDon gioHangHoaDon = new GioHangHoaDon();
        gioHangHoaDon.setIdHoaDon(hoaDon);
        gioHangHoaDon.setIdGioHang(gioHang);
        gioHangHoaDon.setNgayTao(new Date());
        gioHangHoaDon.setNgaySua(new Date());
        return gioHangHoaDonRepository.save(gioHangHoaDon);
    }

    @Override
    public GioHangHoaDon update(GioHangHoaDon hoaDonGioHang, UUID id) {
        return null;
    }

    @Override
    public GioHangHoaDon findById(UUID id) {
        return null;
    }

    @Override
    public List<GioHangHoaDon> getAllByIdHoaDon(UUID id) {
        return gioHangHoaDonRepository.findAllHoaDonGioHangByIdHoaDon(id);
    }



}