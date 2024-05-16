package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.service.IHoaDonChiTietService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietService implements IHoaDonChiTietService {
    @Override
    public List getAll() {
        return null;
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
