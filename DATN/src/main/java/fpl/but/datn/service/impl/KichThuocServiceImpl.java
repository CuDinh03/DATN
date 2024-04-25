package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.repository.KichThuocRepository;
import fpl.but.datn.service.KichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KichThuocServiceImpl implements KichThuocService {

    @Autowired
    KichThuocRepository kichThuocRepository;

    @Override
    public List<KichThuoc> getAll() {
        return kichThuocRepository.findAll();
    }

    @Override
    public KichThuoc getOneById(UUID id) {
        return kichThuocRepository.getOne(id);
    }

    @Override
    public Boolean addKichThuoc(KichThuoc kichThuoc) {
        kichThuocRepository.save(kichThuoc);
        return true;
    }

    @Override
    public Boolean updateKichThuoc(KichThuoc kichThuoc) {
        kichThuocRepository.save(kichThuoc);
        return true;
    }

    @Override
    public Boolean deleteByIdKichThuoc(UUID id) {
        kichThuocRepository.deleteById(id);
        return true;
    }
}
