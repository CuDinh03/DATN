package fpl.but.datn.service.impl;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.SanPham;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.SanPhamRepository;
import fpl.but.datn.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@Service
public class SanPhamService implements ISanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham create(SanPham request) {
        SanPham sanPham = new SanPham();
        Random random = new Random();

        if (sanPhamRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        sanPham.setMa("SP" + random.nextInt(1000));
        sanPham.setTen(request.getTen());
        sanPham.setNgayTao(new Date());
        sanPham.setNgaySua(new Date());
        sanPham.setTrangThai(request.getTrangThai());

        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham update(SanPham request, UUID id) {

        SanPham sanPham = new SanPham();
        sanPham.setId(id);
        sanPham.setMa(request.getMa());
        sanPham.setTen(request.getTen());
        sanPham.setNgayTao(new Date());
        sanPham.setNgaySua(new Date());
        sanPham.setTrangThai(request.getTrangThai());

        return sanPhamRepository.save(sanPham);
    }

    @Override
    public void delete(UUID id) {
        SanPham taiKhoan = findById(id);
        taiKhoan.setTrangThai(0);
        sanPhamRepository.save(taiKhoan);

    }


    @Override
    public void open(UUID id) {
        SanPham taiKhoan = findById(id);
        taiKhoan.setTrangThai(1);
        sanPhamRepository.save(taiKhoan);

    }

    @Override
    public SanPham findById(UUID id) {
        return sanPhamRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NO_LISTSP_FOUND));
    }

    @Override
    public Page<SanPham> getAllSanPhamPageable(Pageable pageable) {
        return sanPhamRepository.findAll(pageable);
    }
}
