package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.HinhAnhRepository;
import fpl.but.datn.service.IHinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@Service
public class HinhAnhService implements IHinhAnhService {

    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Override
    public List<HinhAnh> getAll() {
        return hinhAnhRepository.findAll();
    }

    @Override
    public HinhAnh create(HinhAnh request) {
        HinhAnh hinhAnh = new HinhAnh();
        Random random = new Random();

        if (hinhAnhRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.HINHANH_EXISTED);
        hinhAnh.setId(UUID.randomUUID());
        hinhAnh.setMa("HA" + random.nextInt(1000));
        hinhAnh.setUrl(request.getUrl());
        hinhAnh.setChiTietSanPham(request.getChiTietSanPham());
        hinhAnh.setNgayTao(new Date());
        hinhAnh.setNgaySua(new Date());
        hinhAnh.setTrangThai(request.getTrangThai());
        return hinhAnhRepository.save(hinhAnh);
    }

    @Override
    public HinhAnh update(HinhAnh request, UUID id) {

        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setId(id);
        hinhAnh.setMa(request.getMa());
        hinhAnh.setUrl(request.getUrl());
        hinhAnh.setChiTietSanPham(request.getChiTietSanPham());
        hinhAnh.setNgayTao(new Date());
        hinhAnh.setNgaySua(new Date());
        hinhAnh.setTrangThai(2);
        return hinhAnhRepository.save(hinhAnh);
    }

    @Override
    public void delete(UUID id) {

        HinhAnh taiKhoan = findById(id);
        taiKhoan.setTrangThai(0);
        hinhAnhRepository.save(taiKhoan);
    }

    @Override
    public void open(UUID id) {
        HinhAnh taiKhoan = findById(id);
        taiKhoan.setTrangThai(1);
        hinhAnhRepository.save(taiKhoan);

    }

    @Override
    public HinhAnh findById(UUID id) {
        return hinhAnhRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NO_IMAGES_FOUND));
    }

    @Override
    public Page<HinhAnh> getAllHinhAnhPageable(Pageable pageable) {
        return hinhAnhRepository.findAll(pageable);
    }

    public List finAllByChiTietSanPham(UUID id){
        return hinhAnhRepository.findAllByChiTietSanPham(id);
    }

    @Override
    public List<HinhAnh> saveHinhAnh(List <HinhAnh> list) {
        for (HinhAnh img: list){
            create(img);
        }
        return list;
    }
}
