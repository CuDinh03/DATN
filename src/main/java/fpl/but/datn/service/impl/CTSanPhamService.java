package fpl.but.datn.service.impl;

import fpl.but.datn.entity.*;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.service.ICTSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class CTSanPhamService implements ICTSanPhamService {

    @Autowired
    private CTSanPhamRepository ctSanPhamRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return ctSanPhamRepository.findAll();
    }

    @Override
    public ChiTietSanPham create(ChiTietSanPham request) {

        return null;
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham chiTietSanPham, UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public ChiTietSanPham findById(UUID id) {
        return ctSanPhamRepository.findById(id).get();
    }

    @Override
    public Page<ChiTietSanPham> getAllChiTietSanPhamPageable(Pageable pageable) {
        return ctSanPhamRepository.findAll(pageable);
    }

    @Override
    public List<MauSac> findAllMauSacByMaCTSP(String maChiTietSanPham) {
        return ctSanPhamRepository.findMauSacsByMaSanPhamChiTiet(maChiTietSanPham);
    }@Override
    public List<KichThuoc> findkichThuocsByMaSanPhamChiTiet(String maChiTietSanPham) {
        return ctSanPhamRepository.findkichThuocsByMaSanPhamChiTiet(maChiTietSanPham);
    }

    @Override
    public ChiTietSanPham findChiTietSanPhamByMauSacAndKichThuoc(String ma, UUID kichThuoc, UUID mauSac) {
        return ctSanPhamRepository.findChiTietSanPhamByMauSacAndKichThuoc(ma,kichThuoc,mauSac);
    }

    @Override
    public List<ChiTietSanPham> findSanPhamByKichThuoc(String ma, UUID kichThuoc) {
        return ctSanPhamRepository.findChiTietSanPhamByMaAndKichThuoc(ma, kichThuoc);
    }
}
