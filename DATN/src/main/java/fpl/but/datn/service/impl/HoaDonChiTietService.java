package fpl.but.datn.service.impl;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
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

    @Override
    public List getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public DanhMuc create(HoaDonChiTiet request) {
        Random random = new Random();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa("HD" + random.nextInt(1000));
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        hoaDon.setTrangThai(true);
        hoaDonRepository.save(hoaDon);

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setNgayTao(new Date());
        hoaDonChiTiet.setNgaySua(new Date());
        hoaDonChiTiet.setIdHoaDon(hoaDon);

        return null;
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
}
