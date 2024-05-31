package fpl.but.datn.service.impl;

import fpl.but.datn.entity.*;
import fpl.but.datn.repository.HoaDonChiTietRepository;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IHoaDonChiTietService;

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
        Random random = new Random();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa("HD" + random.nextInt(1000));
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        hoaDon.setTrangThai(true);
        NguoiDung nguoiDung = nguoiDungService.findById(UUID.fromString("324AA318-89E4-4038-AAF0-3452C11A3118"));
        hoaDon.setIdNguoiDung(nguoiDung);
        hoaDonRepository.save(hoaDon);

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setNgayTao(new Date());
        hoaDonChiTiet.setNgaySua(new Date());
        hoaDonChiTiet.setIdHoaDon(hoaDon);
        hoaDonChiTiet.setSoLuong(0);
        hoaDon.setTrangThai(true);
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

//    public List<HoaDonChiTiet> getHoaDonChiTietByHoaDonId(UUID hoaDonId) {
//        return hoaDonChiTietRepository.findChiTietByHoaDonId(hoaDonId);
//    }


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
}
