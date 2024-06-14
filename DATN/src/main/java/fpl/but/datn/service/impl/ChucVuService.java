package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.service.IChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChucVuService implements IChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.findAll();
    }

    @Override
    public ChucVu create(ChucVu request) {
        ChucVu chucVu = new ChucVu();

        if (chucVuRepository.existsByMa(request.getMa())){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        chucVu.setId(UUID.randomUUID());
        chucVu.setMa(request.getMa());
        chucVu.setTen(request.getTen());
        chucVu.setNgayTao(new Date());
        chucVu.setNgaySua(new Date());
        chucVu.setTrangThai(request.getTrangThai());

        return chucVuRepository.save(chucVu);
    }

    @Override
    public ChucVu update(ChucVu request, UUID id) {
        ChucVu chucVu = new ChucVu();

        chucVu.setId(UUID.randomUUID());
        chucVu.setMa(request.getMa());
        chucVu.setId(id);
        chucVu.setTen(request.getTen());
        chucVu.setNgayTao(new Date());
        chucVu.setNgaySua(new Date());
        chucVu.setTrangThai(request.getTrangThai());
        return chucVuRepository.save(chucVu);


    }

    @Override
    public boolean delete(UUID id) {
        Optional<ChucVu> optional = chucVuRepository.findById(id);
        if (optional.isPresent()){
            ChucVu chucVu = optional.get();
            chucVuRepository.delete(chucVu);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public ChucVu findById(UUID id) {
        return chucVuRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }
    public ChucVu getChucVu(UUID id) {
        return chucVuRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROLES_NOT_EXISTED));
    }

    public ChucVu getChucVuByName(String name) {
        return chucVuRepository.findByTen(name)
                .orElseThrow(() -> new AppException(ErrorCode.ROLES_NOT_EXISTED));
    }

}
