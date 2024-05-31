package fpl.but.datn.service.impl;

import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class GioHangChiTietService implements IGioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public GioHangChiTiet create(GioHangChiTiet hoaDonGioHang) {
        return null;
    }

    @Override
    public GioHangChiTiet update(GioHangChiTiet hoaDonGioHang, UUID id) {
        return null;
    }

    @Override
    public GioHangChiTiet findById(UUID id) {
        return null;
    }

    @Override
    public List<GioHangChiTiet> getAllByIdGioHang(UUID id) {
        return gioHangChiTietRepository.findAllByIdGioHang(id);
    }
}
