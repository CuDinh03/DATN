package fpl.but.datn.service.impl;

import fpl.but.datn.entity.MauSac;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.MauSacRepository;
import fpl.but.datn.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MauSacService implements IMauSacService {

    @Autowired
    MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac findById(UUID id) {
        return mauSacRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }

    @Override
    public MauSac add(MauSac request) {

        MauSac mauSac = new MauSac();
        if (mauSacRepository.existsByMa(request.getMa())) {
            throw new AppException(ErrorCode.MA_MAUSAC_TRUNG);
        }

        mauSac.setId(request.getId());
        mauSac.setTen(request.getTen());
        mauSac.setMa(request.getMa());
        mauSac.setNgayTao(new Date());
        mauSac.setNgaySua(new Date());
        mauSac.setTrangThai(request.getTrangThai());

        return mauSac;
    }

    @Override
    public MauSac update(MauSac request, UUID id) {
        MauSac mauSac = new MauSac();

        mauSac.setId(id);
        mauSac.setTen(request.getTen());
        mauSac.setMa(request.getMa());
        mauSac.setNgayTao(request.getNgayTao());
        mauSac.setNgaySua(new Date());
        mauSac.setTrangThai(request.getTrangThai());

        return mauSac;
    }

    @Override
    public Boolean delete(UUID id) {
        Optional<MauSac> optional = mauSacRepository.findById(id);
        if (optional.isPresent()) {
            MauSac mauSac = optional.get();
            mauSacRepository.delete(mauSac);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<MauSac> getAllMauSacDangHoatDong() {
        return mauSacRepository.findAllMauSacDangHoatDong();
    }
}
