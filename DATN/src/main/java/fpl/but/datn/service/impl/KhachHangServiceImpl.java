package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.repository.KhachHangRepository;
import fpl.but.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getOneById(UUID id) {
        return khachHangRepository.getOne(id);
    }

    @Override
    public Boolean addKhachHang(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
        return true;
    }

    @Override
    public Boolean updateKhachHang(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
        return true;
    }

    @Override
    public Boolean deleteByIdKhachHang(UUID id) {
        khachHangRepository.deleteById(id);
        return true;
    }
}
