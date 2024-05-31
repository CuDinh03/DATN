package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.KhachHangRepository;
import fpl.but.datn.service.IKhachHangService;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KhachHangService implements IService<KhachHang>, IKhachHangService {

    @Autowired
    KhachHangRepository khachHangRepository;
    @Override
    public KhachHang getByID(UUID id) {
        return this.khachHangRepository.findById(id).get();
    }

    @Override
    @Transactional
    public KhachHang create(KhachHang request) {
        Optional<KhachHang> existingKhachHang = khachHangRepository.getKhachHangBySdt(request.getSdt());
        if (existingKhachHang.isPresent()) {
            throw new AppException(ErrorCode.SDT_ALREADY_USED);
        }

        KhachHang khachHang = KhachHang.builder()
                .ma("KH" + System.currentTimeMillis())
                .ten(request.getTen())
                .email(request.getEmail())
                .sdt(request.getSdt())
                .gioiTinh(request.getGioiTinh())
                .ngaySinh(request.getNgaySinh())
                .diaChi(request.getDiaChi())
                .ngayTao(new Date())
                .ngaySua(new Date())
                .trangThai(Boolean.TRUE)
                .build();

        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang update(UUID uuid, KhachHang khachHang) {
        return null;
    }

    @Override
    public void delete(UUID id) {
            KhachHang khachHang = this.khachHangRepository.findById(id).get();
            khachHang.setTrangThai(Boolean.FALSE);
            this.khachHangRepository.saveAndFlush(khachHang);
    }

    @Override
    public List<KhachHang> getAll() {
        return this.khachHangRepository.findAll();
    }

    @Override
    public Page<KhachHang> getAllPageable(Pageable pageable) {
        return this.khachHangRepository.findAllPage(pageable);
    }

    @Override
    public KhachHang getKhachHangBySdt(String sdt) {
        return this.khachHangRepository.getKhachHangBySdt(sdt).get();
    }
}
