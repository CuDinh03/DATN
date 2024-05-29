package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.repository.HoaDonChiTietRepository;
import fpl.but.datn.service.IHoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietService implements IHoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public List getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet create(HoaDonChiTiet hoaDonChiTiet) {
        return null;
    }

    @Override
    public HoaDonChiTiet update(HoaDonChiTiet hoaDonChiTiet, UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public HoaDonChiTiet findById(UUID id) {
        return null;
    }
}
