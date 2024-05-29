package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.repository.HinhAnhRepository;
import fpl.but.datn.service.IHinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public HinhAnh create(HinhAnh hinhAnh) {
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
        return null;
    }
}
