package fpl.but.datn.service.impl;

import fpl.but.datn.entity.GioHangHoaDon;
import fpl.but.datn.repository.GioHangHoaDonRepository;
import fpl.but.datn.service.IHoaDonGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class HoaDonGioHangService implements IHoaDonGioHangService {

    @Autowired
    private GioHangHoaDonRepository gioHangHoaDonRepository;

    @Override
    public List getAll() {
        return gioHangHoaDonRepository.findAll();
    }

    @Override
    public GioHangHoaDon create(GioHangHoaDon hoaDonGioHang) {


        return null;
    }

    @Override
    public GioHangHoaDon update(GioHangHoaDon hoaDonGioHang, UUID id) {
        return null;
    }

    @Override
    public GioHangHoaDon findById(UUID id) {
        return null;
    }

    @Override
    public List<GioHangHoaDon> getAllByIdHoaDon(UUID id) {
        return gioHangHoaDonRepository.findAllHoaDonGioHangByIdHoaDon(id);
    }



}
