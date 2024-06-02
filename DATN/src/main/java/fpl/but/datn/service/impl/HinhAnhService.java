package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.HinhAnhRepository;
import fpl.but.datn.service.IHinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class HinhAnhService implements IHinhAnhService {

    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Override
    public List getAll() {
        return hinhAnhRepository.findAll();
    }

    @Override
    public HinhAnh create(HinhAnh request) {
        HinhAnh hinhAnh = new HinhAnh();

        if(hinhAnhRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.HINHANH_EXISTED);
        hinhAnh.setId(UUID.randomUUID());
        hinhAnh.setMa(request.getMa());
        hinhAnh.setUrl(request.getUrl());
        hinhAnh.setNgayTao(new Date());
        hinhAnh.setNgaySua(new Date());
        hinhAnh.setTrangThai(request.getTrangThai());
        return hinhAnhRepository.save(hinhAnh);
    }

    @Override
    public HinhAnh update(HinhAnh hinhAnh, UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public HinhAnh findById(UUID id) {
        return hinhAnhRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HINHANH_NOT_EXISTED));
    }
}
