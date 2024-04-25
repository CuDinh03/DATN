package fpl.but.datn.service.impl;

import fpl.but.datn.entity.NhanVien;
import fpl.but.datn.repository.NhanVienRepository;
import fpl.but.datn.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien getOneById(UUID id) {
        return nhanVienRepository.getOne(id);
    }

    @Override
    public Boolean addNhanVien(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
        return true;
    }

    @Override
    public Boolean updateNhanVien(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
        return true;
    }

    @Override
    public Boolean deleteByIdNhanVien(UUID id) {
        nhanVienRepository.deleteById(id);
        return true;
    }
}
