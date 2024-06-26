package fpl.but.datn.service.impl;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.IGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GioHangService implements IGioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public List<GioHang> getAll() {
        return gioHangRepository.findAll();
    }

    @Transactional
    @Override
    public GioHang create(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    @Override
    public GioHang update(GioHang gioHang, UUID id) {
        Optional<GioHang> optional = gioHangRepository.findById(id);
        return optional.map(o -> {
            o.setTrangThai(gioHang.getTrangThai());
            o.setNgaySua(new Date());
            return gioHangRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<GioHang> optional = gioHangRepository.findById(id);
        if (optional.isPresent()){
            GioHang gioHang = optional.get();
            gioHangRepository.delete(gioHang);
            return true;
        }else {
            return false;
        }
    }
    @Override
    public GioHang findById(UUID id) {
        return gioHangRepository.findById(id).get();
    }

    @Override
    public GioHang findByIdKhachHang(UUID id) {
        return gioHangRepository.findGioHangByKhachHang(id);
    }
}
