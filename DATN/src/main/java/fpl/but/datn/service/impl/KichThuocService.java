package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.KichThuocRepository;
import fpl.but.datn.service.IKichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KichThuocService implements IKichThuocService {

    @Autowired
    KichThuocRepository kichThuocRepository;

    @Override
    public List<KichThuoc> getAll() {
        return kichThuocRepository.findAll();
    }

    @Override
    public KichThuoc findById(UUID id) {
        return kichThuocRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }

    @Override
    public KichThuoc add(KichThuoc request) {

        KichThuoc kichThuoc1 = new KichThuoc();

        if (kichThuocRepository.existsByMa(request.getMa())) {
            throw new AppException(ErrorCode.MA_KICHTHUOC_TRUNG);
        }

        kichThuoc1.setId(UUID.randomUUID());
        kichThuoc1.setMa(request.getMa());
        kichThuoc1.setTen(request.getTen());
        kichThuoc1.setNgaySua(new Date());
        kichThuoc1.setNgayTao(new Date());
        kichThuoc1.setTrangThai(request.getTrangThai());

        return kichThuocRepository.save(kichThuoc1);

    }

    @Override
    public KichThuoc update(KichThuoc request, UUID id) {
        KichThuoc kichThuoc1 = new KichThuoc();

        kichThuoc1.setId(id);
        kichThuoc1.setMa(request.getMa());
        kichThuoc1.setTen(request.getTen());
        kichThuoc1.setNgaySua(new Date());
        kichThuoc1.setNgayTao(request.getNgayTao());
        kichThuoc1.setTrangThai(request.getTrangThai());

        return kichThuocRepository.save(kichThuoc1);
    }

    @Override
    public Boolean delete(UUID id) {
        Optional<KichThuoc> kichThuocOptional = kichThuocRepository.findById(id);
        if (kichThuocOptional.isPresent()){
            KichThuoc kichThuoc1 = kichThuocOptional.get();
            kichThuocRepository.delete(kichThuoc1);
            return true;
        }
        return false;
    }

    @Override
    public List<KichThuoc> getAllKichThuocDangHoatDong() {
        return kichThuocRepository.findAllKichThuocDangHoatDong();
    }
}
