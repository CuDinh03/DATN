package fpl.but.datn.service.impl;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonService implements IHoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Override
    public List getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public DanhMuc create(HoaDon hoaDon) {
        return null;
    }

    @Override
    public HoaDon update(HoaDon hoaDon, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        HoaDon hoaDon = findById(id);
        hoaDon.setTrangThai(Boolean.FALSE);
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void open(UUID id) {
    }

    @Override
    public HoaDon findById(UUID id) {
        return hoaDonRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
    }

    @Override
    public Page<HoaDon> getAllDanhMucPageable(Pageable pageable) {
        return null;
    }


}
