package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.ThuongHieu;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.ThuongHieuRepository;
import fpl.but.datn.service.IThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ThuongHieuService implements IThuongHieuService {
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;
    @Override
    public List getAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu create(ThuongHieu request) {
        ThuongHieu thuongHieu = new ThuongHieu();
        Random random = new Random();

        if (thuongHieuRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.THUONGHIEU_EXISTED);
        thuongHieu.setMa("TH" + random.nextInt(1000));
        thuongHieu.setTen(request.getTen());
        thuongHieu.setNgayTao(new Date());
        thuongHieu.setNgaySua(new Date());
        thuongHieu.setTrangThai(request.getTrangThai());

        return thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public ThuongHieu update(ThuongHieu request, UUID id) {
        ThuongHieu thuongHieu = new ThuongHieu();
        thuongHieu.setId(id);
        thuongHieu.setMa(request.getMa());
        thuongHieu.setTen(request.getTen());
        thuongHieu.setNgayTao(new Date());
        thuongHieu.setNgaySua(new Date());
        thuongHieu.setTrangThai(request.getTrangThai());
        return thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public void delete(UUID id) {
        ThuongHieu taiKhoan = findById(id);
        taiKhoan.setTrangThai(0);
        thuongHieuRepository.save(taiKhoan);

    }

    @Override
    public void open(UUID id) {
        ThuongHieu taiKhoan = findById(id);
        taiKhoan.setTrangThai(1);
        thuongHieuRepository.save(taiKhoan);

    }

    @Override
    public ThuongHieu findById(UUID id) {
        return thuongHieuRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NO_THUONGHIEU_FOUND));
    }

    @Override
    public Page<ThuongHieu> getAllThuongHieuPageable(Pageable pageable) {
        return thuongHieuRepository.findAll(pageable);
    }
}
