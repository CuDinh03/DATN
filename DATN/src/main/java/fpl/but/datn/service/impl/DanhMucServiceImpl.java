package fpl.but.datn.service.impl;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.DanhMucRepository;
import fpl.but.datn.service.IDanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DanhMucServiceImpl implements IDanhMucService {

    @Autowired
    private DanhMucRepository danhMucRepository;
    @Override
    public List<DanhMuc> getAll() {
        return danhMucRepository.findAll();
    }
    @Override
    public DanhMuc create(DanhMuc request) {
        DanhMuc danhMuc = new DanhMuc();

        if (danhMucRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        danhMuc.setMa(request.getMa());
        danhMuc.setTen(request.getTen());
        danhMuc.setNgayTao(new Date());
        danhMuc.setNgaySua(new Date());
        danhMuc.setTrangThai(request.getTrangThai());

        return danhMucRepository.save(danhMuc);
    }

    @Override
    public DanhMuc update(DanhMuc request, UUID id) {
        DanhMuc danhMuc = new DanhMuc();

        danhMuc.setId(UUID.randomUUID());
        danhMuc.setMa(request.getMa());
        danhMuc.setTen(request.getTen());
        danhMuc.setNgayTao(new Date());
        danhMuc.setNgaySua(new Date());
        danhMuc.setTrangThai(request.getTrangThai());

        return danhMucRepository.save(danhMuc);

    }



    @Override
    public DanhMuc findById(UUID id) {

        return danhMucRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
    }
    @Override
    public void delete(UUID id) {
        DanhMuc taiKhoan = findById(id);
        taiKhoan.setTrangThai(Boolean.FALSE);
        danhMucRepository.save(taiKhoan);

    }

    public void open(UUID id) {
        DanhMuc taiKhoan = findById(id);
        taiKhoan.setTrangThai(Boolean.TRUE);
        danhMucRepository.save(taiKhoan);

    }

    @Override
    public Page<DanhMuc> getAllDanhMucPageable(Pageable pageable) {
        return danhMucRepository.findAll(pageable);
    }
}
