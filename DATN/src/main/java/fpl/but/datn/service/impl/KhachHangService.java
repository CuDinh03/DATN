package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.KhachHangRepository;
import fpl.but.datn.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KhachHangService implements IKhachHangService {

    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang findById(UUID id) {
        return khachHangRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }

    @Override
    public KhachHang add(KhachHang request) {
        KhachHang khachHang1 = new KhachHang();

        if (khachHangRepository.existsByMa(request.getMa())) {
            throw new AppException(ErrorCode.MA_NHANVIEN_TRUNG);
        }

        khachHang1.setId(UUID.randomUUID());
        khachHang1.setMa(request.getMa());
        khachHang1.setTen(request.getTen());
        khachHang1.setEmail(request.getEmail());
        khachHang1.setSdt(request.getSdt());
        khachHang1.setGioiTinh(request.getGioiTinh());
        khachHang1.setNgaySinh(request.getNgaySinh());
        khachHang1.setDiaChi(request.getDiaChi());
        khachHang1.setNgaySua(new Date());
        khachHang1.setNgayTao(new Date());
        khachHang1.setTrangThai(request.getTrangThai());

        return khachHangRepository.save(khachHang1);
    }

    @Override
    public KhachHang update(KhachHang request, UUID id) {

        KhachHang khachHang1 = new KhachHang();

        khachHang1.setId(id);
        khachHang1.setMa(request.getMa());
        khachHang1.setTen(request.getTen());
        khachHang1.setEmail(request.getEmail());
        khachHang1.setSdt(request.getSdt());
        khachHang1.setGioiTinh(request.getGioiTinh());
        khachHang1.setNgaySinh(request.getNgaySinh());
        khachHang1.setDiaChi(request.getDiaChi());
        khachHang1.setNgaySua(new Date());
        khachHang1.setNgayTao(request.getNgayTao());
        khachHang1.setTrangThai(request.getTrangThai());

        return khachHangRepository.save(khachHang1);
    }

    @Override
    public Boolean delete(UUID id) {
        Optional<KhachHang> khachHangOptional = khachHangRepository.findById(id);
        if (khachHangOptional.isPresent()){
            KhachHang khachHang = khachHangOptional.get();
            khachHangRepository.delete(khachHang);
            return true;
        }
        return false;
    }


}
