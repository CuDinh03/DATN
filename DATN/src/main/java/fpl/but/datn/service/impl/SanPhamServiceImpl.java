package fpl.but.datn.service.impl;

import fpl.but.datn.entity.SanPham;
import fpl.but.datn.repository.SanPhamRepository;
import fpl.but.datn.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements InterfaceService<SanPham> {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham addNew(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham update(SanPham sanPham, UUID id) {
        Optional<SanPham> optional = sanPhamRepository.findById(id);
        return optional.map(o -> {
            o.setMa(sanPham.getMa());
            o.setTen(sanPham.getTen());
            o.setMoTa(sanPham.getMoTa());
            o.setNgayTao(sanPham.getNgayTao());
            o.setNgaySua(sanPham.getNgaySua());
            o.setTrangThai(sanPham.isTrangThai());
            return sanPhamRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<SanPham> optional = sanPhamRepository.findById(id);
        if (optional.isPresent()){
            SanPham sanPham = optional.get();
            sanPhamRepository.delete(sanPham);
            return true;
        }
        return false;
    }

    @Override
    public SanPham findById(UUID id) {
        return sanPhamRepository.findById(id).get();
    }
}
