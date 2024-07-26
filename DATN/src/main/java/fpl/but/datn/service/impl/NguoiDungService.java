package fpl.but.datn.service.impl;

import fpl.but.datn.entity.NguoiDung;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.NguoiDungRepository;
import fpl.but.datn.service.INguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class NguoiDungService implements INguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public NguoiDung create(NguoiDung nguoiDung) {
        return null;
    }

    @Override
    public NguoiDung update(NguoiDung nguoiDung, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void open(UUID id) {

    }

    @Override
    public NguoiDung findById(UUID id) {
        return nguoiDungRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public Page<NguoiDung> getAllDanhMucPageable(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<NguoiDung> findByTaiKhoanid(UUID id) {
        return nguoiDungRepository.findByTaiKhoanid(id);
    }



}
