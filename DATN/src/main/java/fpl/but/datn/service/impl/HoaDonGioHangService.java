package fpl.but.datn.service.impl;

import fpl.but.datn.entity.*;

import fpl.but.datn.repository.*;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangHoaDonRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.IHoaDonGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private NguoiDungService nguoiDungService;

    @Override
    public List getAll() {
        return gioHangHoaDonRepository.findAllByTrangThai();
    }


    @Override
    public GioHangHoaDon create(GioHangHoaDon hoaDonGioHang) {
        Random random = new Random();
        // Tạo giỏ hàng
        GioHang gioHang = new GioHang();
        gioHang.setMa("GH" + random.nextInt(1000));
        gioHang.setNgayTao(new Date());
        gioHang.setNgaySua(new Date());
        gioHang.setTrangThai(1);
        gioHangRepository.save(gioHang);

        BigDecimal tongTien = BigDecimal.ZERO;
        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietRepository.findAllByIdGioHang(gioHang.getId());
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            tongTien = tongTien.add(gioHangChiTiet.getChiTietSanPham().getGiaBan().multiply(new BigDecimal(gioHangChiTiet.getSoLuong())));
        }

        // Tạo hóa đơn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa("HD" + random.nextInt(1000));
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        hoaDon.setTrangThai(0);
        NguoiDung nguoiDung = nguoiDungService.findById(UUID.fromString("e555686c-39d8-427d-a442-b4789cc57891"));
        hoaDon.setNguoiDung(nguoiDung);
        hoaDon.setTongTien(tongTien);
        hoaDonRepository.save(hoaDon);

        // Tạo giỏ hàng hóa đơn
        GioHangHoaDon gioHangHoaDon = new GioHangHoaDon();
        gioHangHoaDon.setHoaDon(hoaDon);
        gioHangHoaDon.setGioHang(gioHang);
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
    public GioHangHoaDon findByIdHoaDon(UUID idHoaDon) {
        return gioHangHoaDonRepository.findByIdHoaDon(idHoaDon);
    }


}