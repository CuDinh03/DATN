package fpl.but.datn.service.impl;

import fpl.but.datn.entity.*;
import fpl.but.datn.repository.HoaDonChiTietRepository;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IHoaDonChiTietService;
import fpl.but.datn.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HoaDonChiTietService implements IHoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private NguoiDungService nguoiDungService;

    @Override
    public List getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet create(HoaDonChiTiet request) {
//        Random random = new Random();
//        HoaDon hoaDon = new HoaDon();
//        hoaDon.setMa("HD" + random.nextInt(1000));
//        hoaDon.setNgayTao(new Date());
//        hoaDon.setNgaySua(new Date());
//        hoaDon.setTrangThai(true);
//        NguoiDung nguoiDung = nguoiDungService.findById(UUID.fromString("767ce273-301c-4c64-8039-5c92f4285ccb"));
//        hoaDon.setNguoiDung(nguoiDung);
//        hoaDonRepository.save(hoaDon);
//
//        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
//        hoaDonChiTiet.setNgayTao(new Date());
//        hoaDonChiTiet.setNgaySua(new Date());
//        hoaDonChiTiet.setHoaDon(hoaDon);
//        hoaDonChiTiet.setSoLuong(0);
//        hoaDon.setTrangThai(true);
        return hoaDonChiTietRepository.save(request);
    }

    @Override
    public HoaDonChiTiet update(HoaDonChiTiet hoaDonChiTiet, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void open(UUID id) {

    }

    @Override
    public HoaDonChiTiet findById(UUID id) {
        return null;
    }

    @Override
    public Page<HoaDonChiTiet> getAllDanhMucPageable(Pageable pageable) {
        return null;
    }

    public List<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(UUID idHoaDon) {
        return hoaDonChiTietRepository.findAllHoaDonChiTietByIdHoaDon(idHoaDon);
    }
}