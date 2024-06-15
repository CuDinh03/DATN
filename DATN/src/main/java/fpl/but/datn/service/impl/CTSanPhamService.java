package fpl.but.datn.service.impl;


import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.ICTSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CTSanPhamService implements ICTSanPhamService {

    @Autowired
    private CTSanPhamRepository ctSanPhamRepository;
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return ctSanPhamRepository.findAll();
    }

    @Override
    public ChiTietSanPham create(ChiTietSanPham request) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        Random random = new Random();

        if (ctSanPhamRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        ctsp.setMa("CTSP" + random.nextInt(1000));
        ctsp.setId(request.getId());
        ctsp.setMa(request.getMa());
        ctsp.setHinhAnh(request.getHinhAnh());
        ctsp.setThuongHieu(request.getThuongHieu());
        ctsp.setChatLieu(request.getChatLieu());
        ctsp.setDanhMuc(request.getDanhMuc());
        ctsp.setKichThuoc(request.getKichThuoc());
        ctsp.setMauSac(request.getMauSac());
        ctsp.setSoLuong(request.getSoLuong());
        ctsp.setGiaNhap(request.getGiaNhap());
        ctsp.setGiaBan(request.getGiaBan());
        ctsp.setNgayNhap(request.getNgayNhap());
        ctsp.setNgayTao(new Date());
        ctsp.setNgaySua(new Date());
        ctsp.setTrangThai(request.getTrangThai());

        return ctSanPhamRepository.save(ctsp);

    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham request, UUID id) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setId(id);
        ctsp.setMa(request.getMa());
        ctsp.setSanPham(request.getSanPham());
        ctsp.setHinhAnh(request.getHinhAnh());
        ctsp.setThuongHieu(request.getThuongHieu());
        ctsp.setChatLieu(request.getChatLieu());
        ctsp.setDanhMuc(request.getDanhMuc());
        ctsp.setKichThuoc(request.getKichThuoc());
        ctsp.setMauSac(request.getMauSac());
        ctsp.setSoLuong(request.getSoLuong());
        ctsp.setGiaNhap(request.getGiaNhap());
        ctsp.setGiaBan(request.getGiaBan());
        ctsp.setNgayNhap(request.getNgayNhap());
        ctsp.setNgayTao(new Date());
        ctsp.setNgaySua(new Date());
        ctsp.setTrangThai(request.getTrangThai());

        return ctSanPhamRepository.save(ctsp);

    }

    @Override
    public boolean delete(UUID id) {
        Optional<ChiTietSanPham> optional = ctSanPhamRepository.findById(id);
        if (optional.isPresent()){
            ChiTietSanPham chiTietSanPham = optional.get();
            ctSanPhamRepository.delete(chiTietSanPham);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void open(UUID id) {
            ChiTietSanPham taiKhoan = findById(id);
            taiKhoan.setTrangThai(1);
            ctSanPhamRepository.save(taiKhoan);

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
