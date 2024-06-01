package fpl.but.datn.service.impl;

import fpl.but.datn.entity.NhanVien;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.NhanVienRepository;
import fpl.but.datn.service.INhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NhanVienService implements INhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien findById(UUID id) {
        return nhanVienRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }

    @Override
    public NhanVien add(NhanVien request) {
        NhanVien nhanVien1 = new NhanVien();

        if (nhanVienRepository.existsByMa(request.getMa())) {
            throw new AppException(ErrorCode.MA_NHANVIEN_TRUNG);
        }

        nhanVien1.setId(UUID.randomUUID());
        nhanVien1.setMa(request.getMa());
        nhanVien1.setTen(request.getTen());
        nhanVien1.setEmail(request.getEmail());
        nhanVien1.setSdt(request.getSdt());
        nhanVien1.setGioiTinh(request.getGioiTinh());
        nhanVien1.setNgaySinh(request.getNgaySinh());
        nhanVien1.setDiaChi(request.getDiaChi());
        nhanVien1.setNgaySua(new Date());
        nhanVien1.setNgayTao(new Date());
        nhanVien1.setTrangThai(request.getTrangThai());

        return nhanVienRepository.save(nhanVien1);
    }

    @Override
    public NhanVien update(NhanVien request, UUID id) {

        NhanVien nhanVien1 = new NhanVien();

        nhanVien1.setId(id);
        nhanVien1.setMa(request.getMa());
        nhanVien1.setTen(request.getTen());
        nhanVien1.setEmail(request.getEmail());
        nhanVien1.setSdt(request.getSdt());
        nhanVien1.setGioiTinh(request.getGioiTinh());
        nhanVien1.setNgaySinh(request.getNgaySinh());
        nhanVien1.setDiaChi(request.getDiaChi());
        nhanVien1.setNgaySua(new Date());
        nhanVien1.setNgayTao(request.getNgayTao());
        nhanVien1.setTrangThai(request.getTrangThai());

        return nhanVienRepository.save(nhanVien1);
    }

    @Override
    public Boolean delete(UUID id) {
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(id);
        if (nhanVienOptional.isPresent()){
            NhanVien nhanVien = nhanVienOptional.get();
            nhanVienRepository.delete(nhanVien);
            return true;
        }
        return false;
    }
}
