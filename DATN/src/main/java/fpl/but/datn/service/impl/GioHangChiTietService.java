package fpl.but.datn.service.impl;

import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class GioHangChiTietService implements IGioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Override
    public List getAll() {
        return gioHangChiTietRepository.findAll();
    }

    @Override
    public GioHangChiTiet create(GioHangChiTiet gioHangChiTiet) {
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public GioHangChiTiet update(GioHangChiTiet gioHangChiTiet, UUID id) {
        Optional<GioHangChiTiet> optional = gioHangChiTietRepository.findById(id);
        return optional.map(o -> {
            o.setIdGioHang(gioHangChiTiet.getIdGioHang());
            o.setIdSanPham(gioHangChiTiet.getIdSanPham());
            o.setSoLuong(gioHangChiTiet.getSoLuong());
            o.setTrangThai(gioHangChiTiet.getTrangThai());
            return gioHangChiTietRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<GioHangChiTiet> optional = gioHangChiTietRepository.findById(id);
        if (optional.isPresent()){
            GioHangChiTiet gioHangChiTiet = optional.get();
            gioHangChiTietRepository.delete(gioHangChiTiet);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public GioHangChiTiet findById(UUID id) {
        return gioHangChiTietRepository.findById(id).get();
    }
}
