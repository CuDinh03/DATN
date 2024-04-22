package fpl.but.datn.service.Impl;

import fpl.but.datn.dto.GioHangDto;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GioHangServiceImpl implements IService<GioHang> {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public List<GioHang> getAll() {
        return gioHangRepository.findAll();
    }

    @Override
    public GioHang addNew(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    @Override
    public GioHang update(GioHang gioHang, UUID id) {
        Optional<GioHang> optional = gioHangRepository.findById(id);
        return optional.map(o -> {
            o.setMa(gioHang.getMa());
            o.setIdKhachHang(gioHang.getIdKhachHang());
            o.setTrangThai(gioHang.isTrangThai());
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
}
