package fpl.but.datn.service.impl;

import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GioHangChiTietServiceImpl implements IService<GioHangChiTiet> {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Override
    public List<GioHangChiTiet> getAll() {
        return gioHangChiTietRepository.findAll();
    }

    @Override
    public GioHangChiTiet addNew(GioHangChiTiet gioHangChiTiet) {
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public GioHangChiTiet update(GioHangChiTiet gioHangChiTiet, UUID id) {
        Optional<GioHangChiTiet> optional = gioHangChiTietRepository.findById(id);
        return optional.map(o -> {
            o.setMa(gioHangChiTiet.getMa());
            o.setIdGioHang(gioHangChiTiet.getIdGioHang());
            o.setIdSanPham(gioHangChiTiet.getIdSanPham());
            o.setSoLuong(gioHangChiTiet.getSoLuong());
            o.setNgayTao(gioHangChiTiet.getNgayTao());
            o.setNgaySua(gioHangChiTiet.getNgaySua());
            o.setTrangThai(gioHangChiTiet.isTrangThai());
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
