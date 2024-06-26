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

    @Override
    public List<Object[]> findAllChiTietAndHinhAnhByIdHoaDon(UUID idGioHang) {

        return hoaDonChiTietRepository.findAllChiTietAndHinhAnhByIdHoaDon(idGioHang);
    }

}
