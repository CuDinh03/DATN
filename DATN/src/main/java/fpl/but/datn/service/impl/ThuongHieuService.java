package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ThuongHieu;
import fpl.but.datn.repository.ThuongHieuRepository;
import fpl.but.datn.service.IThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ThuongHieuService implements IThuongHieuService {


    @Autowired
    ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu findById(UUID id) {
        return null;
    }

    @Override
    public ThuongHieu add(ThuongHieu thuongHieu) {
        return null;
    }

    @Override
    public ThuongHieu update(ThuongHieu thuongHieu, UUID id) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }

    @Override
    public List<ThuongHieu> getAllThuongHieuDangHoatDong() {
        return thuongHieuRepository.findAllThuongHieuDangHoatDong();
    }
}
