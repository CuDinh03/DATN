package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonServiceImpl implements IService<HoaDon> {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon addNew(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon update(HoaDon hoaDon, UUID id) {
        Optional<HoaDon> optional = hoaDonRepository.findById(id);
        return optional.map(o -> {
            o.setMa(hoaDon.getMa());
            o.setIdNhanVien(hoaDon.getIdNhanVien());
            o.setIdKhachHang(hoaDon.getIdKhachHang());
            o.setIdVoucher(hoaDon.getIdVoucher());
            o.setTongTien(hoaDon.getTongTien());
            o.setTongTienGiam(hoaDon.getTongTienGiam());
            o.setGhiChu(hoaDon.getGhiChu());
            o.setNgayTao(hoaDon.getNgayTao());
            o.setNgaySua(hoaDon.getNgaySua());
            o.setTrangThai(hoaDon.isTrangThai());
            return hoaDonRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<HoaDon> optional = hoaDonRepository.findById(id);
        if (optional.isPresent()){
            HoaDon hoaDon = optional.get();
            hoaDonRepository.delete(hoaDon);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public HoaDon findById(UUID id) {
        return hoaDonRepository.findById(id).get();
    }
}
