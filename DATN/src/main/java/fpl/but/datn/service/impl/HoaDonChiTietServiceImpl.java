package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.repository.HoaDonChiTietRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements IService<HoaDonChiTiet> {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public List<HoaDonChiTiet> getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet addNew(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTiet update(HoaDonChiTiet hoaDonChiTiet, UUID id) {
        Optional<HoaDonChiTiet> optional = hoaDonChiTietRepository.findById(id);
        return optional.map(o -> {
            o.setMa(hoaDonChiTiet.getMa());
            o.setIdSanPham(hoaDonChiTiet.getIdSanPham());
            o.setIdHoaDon(hoaDonChiTiet.getIdHoaDon());
            o.setSoLuong(hoaDonChiTiet.getSoLuong());
            o.setGiaBan(hoaDonChiTiet.getGiaBan());
            o.setTrangThai(hoaDonChiTiet.isTrangThai());
            return hoaDonChiTietRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<HoaDonChiTiet> optional = hoaDonChiTietRepository.findById(id);
        if (optional.isPresent()){
            HoaDonChiTiet hoaDonChiTiet = optional.get();
            hoaDonChiTietRepository.delete(hoaDonChiTiet);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public HoaDonChiTiet findById(UUID id) {
        return hoaDonChiTietRepository.findById(id).get();
    }
}
