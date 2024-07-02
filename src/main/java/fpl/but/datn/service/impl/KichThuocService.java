package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.MauSac;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.KichThuocRepository;
import fpl.but.datn.service.IKichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class KichThuocService implements IKichThuocService {

    @Autowired
    private KichThuocRepository kichThuocRepository;
    @Override
    public List getAll() {
        return kichThuocRepository.findAll();
    }

    @Override
    public KichThuoc create(KichThuoc request) {
        KichThuoc kichThuoc = new KichThuoc();
        Random random = new Random();

        if (kichThuocRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.KICHTHUOC_EXISTED);
        kichThuoc.setMa("KT" + random.nextInt(1000));
        kichThuoc.setTen(request.getTen());
        kichThuoc.setNgayTao(new Date());
        kichThuoc.setNgaySua(new Date());
        kichThuoc.setTrangThai(request.getTrangThai());

        return kichThuocRepository.save(kichThuoc);
    }

    @Override
    public KichThuoc update(KichThuoc request, UUID id) {
        KichThuoc kichThuoc = new KichThuoc();
        kichThuoc.setId(id);
        kichThuoc.setMa(request.getMa());
        kichThuoc.setTen(request.getTen());
        kichThuoc.setNgayTao(new Date());
        kichThuoc.setNgaySua(new Date());
        kichThuoc.setTrangThai(request.getTrangThai());
        return kichThuocRepository.save(kichThuoc);
    }

    @Override
    public void delete(UUID id) {
        KichThuoc taiKhoan = findById(id);
        taiKhoan.setTrangThai(0);
        kichThuocRepository.save(taiKhoan);

    }

    @Override
    public void open(UUID id) {
        KichThuoc taiKhoan = findById(id);
        taiKhoan.setTrangThai(1);
        kichThuocRepository.save(taiKhoan);

    }

    @Override
    public KichThuoc findById(UUID id) {
        return kichThuocRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NO_KICHTHUOC_FOUND));
    }

    @Override
    public Page<KichThuoc> getAllKichThuocPageable(Pageable pageable) {
        return kichThuocRepository.findAll(pageable);
    }

    @Override
    public List<KichThuoc> getAllKichThuocDangHoatDong() {
        return kichThuocRepository.findAllKichThuocDangHoatDong();
    }
}
