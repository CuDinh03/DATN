package fpl.but.datn.service.impl;

import fpl.but.datn.entity.MauSac;
import fpl.but.datn.repository.MauSacRepository;
import fpl.but.datn.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac getOneById(UUID id) {
        return mauSacRepository.getOne(id);
    }

    @Override
    public Boolean addMauSac(MauSac mauSac) {
        mauSacRepository.save(mauSac);
        return true;
    }

    @Override
    public Boolean updateMauSac(MauSac mauSac) {
        mauSacRepository.save(mauSac);
        return true;
    }

    @Override
    public Boolean deleteByIdMauSac(UUID id) {
        mauSacRepository.deleteById(id);
        return true;
    }
}
