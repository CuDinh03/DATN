package fpl.but.datn.service.Impl;

import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;
    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.findAll();
    }

    @Override
    public ChucVu addNew(ChucVu chucVu) {
        return chucVuRepository.save(chucVu);
    }

    @Override
    public ChucVu update(ChucVu chucVu, UUID id) {
        Optional<ChucVu> optional = chucVuRepository.findById(id);
        return optional.map(o -> {
            o.setMa(chucVu.getMa());
            o.setMoTa(chucVu.getMoTa());
            o.setTen(chucVu.getTen());
            o.setTrangThai(chucVu.isTrangThai());
            return chucVuRepository.save(o);
        }).orElse(null);

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
        return chucVuRepository.findById(id).get();
    }

}
