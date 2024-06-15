package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.MauSac;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.MauSacRepository;
import fpl.but.datn.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class MauSacService implements IMauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac create(MauSac request) {
        MauSac mauSac = new MauSac();
        Random random = new Random();

        if (mauSacRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.MAUSAC_EXISTED);
        mauSac.setMa("MS" + random.nextInt(1000));
        mauSac.setTen(request.getTen());
        mauSac.setNgayTao(new Date());
        mauSac.setNgaySua(new Date());
        mauSac.setTrangThai(request.getTrangThai());

        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac update(MauSac request, UUID id) {
        MauSac mauSac = new MauSac();
        mauSac.setId(id);
        mauSac.setMa(request.getMa());
        mauSac.setTen(request.getTen());
        mauSac.setNgayTao(new Date());
        mauSac.setNgaySua(new Date());
        mauSac.setTrangThai(request.getTrangThai());
        return mauSacRepository.save(mauSac);
    }

    @Override
    public void delete(UUID id) {
        MauSac taiKhoan = findById(id);
        taiKhoan.setTrangThai(0);
        mauSacRepository.save(taiKhoan);

    }

    @Override
    public void open(UUID id) {
        MauSac taiKhoan = findById(id);
        taiKhoan.setTrangThai(1);
        mauSacRepository.save(taiKhoan);

    }

    @Override
    public MauSac findById(UUID id) {
        return mauSacRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NO_MAUSAC_FOUND));
    }

    @Override
    public Page<MauSac> getAllMauSacPageable(Pageable pageable) {
        return mauSacRepository.findAll(pageable);
    }
}
