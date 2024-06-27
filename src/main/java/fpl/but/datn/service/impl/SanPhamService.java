package fpl.but.datn.service.impl;

import fpl.but.datn.entity.SanPham;
import fpl.but.datn.repository.SanPhamRepository;
import fpl.but.datn.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class SanPhamService implements ISanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Override
    public List getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham create(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham update(SanPham sanPham, UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public SanPham findById(UUID id) {
        return sanPhamRepository.findById(id).get();
    }
}
