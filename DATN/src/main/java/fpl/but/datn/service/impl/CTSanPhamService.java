package fpl.but.datn.service.impl;

import fpl.but.datn.entity.*;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.ICTSanPhamService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public Page<ChiTietSanPham> getAllChiTietSanPhamPageableSapXepNGayTao(Pageable pageable) {
        return ctSanPhamRepository.findAllSapXepNgayTao(pageable);
    }

    @Override
    public List<MauSac> findAllMauSacByMaCTSP(String maChiTietSanPham) {
        return null;
    }

    @Override
    public List<KichThuoc> findkichThuocsByMaSanPhamChiTiet(String maChiTietSanPham) {
        return null;
    }

    @Override
    public ChiTietSanPham findChiTietSanPhamByMauSacAndKichThuoc(String ma, UUID kichThuoc, UUID mauSac) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findSanPhamByKichThuoc(String ma, UUID kichThuoc) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPBySanPhamId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByChatLieuId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByDanhMucId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByKichThuocId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByMauSacId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByThuongHieuId(UUID id) {
        return null;
    }

    @Override
    public ChiTietSanPham create(ChiTietSanPham request) {

        ChiTietSanPham chiTietSanPham;

        // Kiểm tra xem sản phẩm chi tiết đã tồn tại chưa
        long count = ctSanPhamRepository.countByCriteria(
                request.getMa(),
                request.getSanPham(),
                request.getThuongHieu(),
                request.getChatLieu(),
                request.getDanhMuc(),
                request.getKichThuoc(),
                request.getMauSac());

        if (count > 0) {
            // Tìm sản phẩm chi tiết đã tồn tại
            chiTietSanPham = ctSanPhamRepository.findByCriteria(
                    request.getMa(),
                    request.getSanPham(),
                    request.getThuongHieu(),
                    request.getChatLieu(),
                    request.getDanhMuc(),
                    request.getKichThuoc(),
                    request.getMauSac());

            // Cập nhật số lượng của sản phẩm chi tiết
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + request.getSoLuong());
            chiTietSanPham.setNgaySua(new Date());
        } else {
            // Tạo mới sản phẩm chi tiết
            chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setMa(request.getMa());
            chiTietSanPham.setSanPham(request.getSanPham());
            chiTietSanPham.setThuongHieu(request.getThuongHieu());
            chiTietSanPham.setChatLieu(request.getChatLieu());
            chiTietSanPham.setDanhMuc(request.getDanhMuc());
            chiTietSanPham.setKichThuoc(request.getKichThuoc());
            chiTietSanPham.setMauSac(request.getMauSac());
            chiTietSanPham.setSoLuong(request.getSoLuong());
            chiTietSanPham.setGiaNhap(request.getGiaNhap());
            chiTietSanPham.setGiaBan(request.getGiaBan());
            chiTietSanPham.setNgayNhap(new Date());
            chiTietSanPham.setNgayTao(new Date());
            chiTietSanPham.setNgaySua(new Date());
            chiTietSanPham.setTrangThai(1);
            chiTietSanPham.setHinhAnh(request.getHinhAnh());
        }

        // Lưu sản phẩm chi tiết
        return ctSanPhamRepository.save(chiTietSanPham);
    }


    @Override
    public ChiTietSanPham update(ChiTietSanPham request, UUID id) {

        ChiTietSanPham chiTietSanPhamoldValue = ctSanPhamRepository.findById(id).get();

        chiTietSanPhamoldValue.setMa(request.getMa());
        chiTietSanPhamoldValue.setSanPham(request.getSanPham());
        chiTietSanPhamoldValue.setThuongHieu(request.getThuongHieu());
        chiTietSanPhamoldValue.setChatLieu(request.getChatLieu());
        chiTietSanPhamoldValue.setDanhMuc(request.getDanhMuc());
        chiTietSanPhamoldValue.setKichThuoc(request.getKichThuoc());
        chiTietSanPhamoldValue.setMauSac(request.getMauSac());
        chiTietSanPhamoldValue.setSoLuong(request.getSoLuong());
        chiTietSanPhamoldValue.setGiaNhap(request.getGiaNhap());
        chiTietSanPhamoldValue.setGiaBan(request.getGiaBan());
        chiTietSanPhamoldValue.setNgayNhap(request.getNgayNhap());
        chiTietSanPhamoldValue.setNgayTao(request.getNgayTao());
        chiTietSanPhamoldValue.setNgaySua(new Date());
        chiTietSanPhamoldValue.setTrangThai(1);
        chiTietSanPhamoldValue.setHinhAnh(request.getHinhAnh());

        return ctSanPhamRepository.save(chiTietSanPhamoldValue);
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
    public ChiTietSanPham findById(UUID id) {
        return ctSanPhamRepository.findById(id).get();
    }

    @Override
    public Page<ChiTietSanPham> getAllChiTietSanPhamPageable(Pageable pageable) {
        return ctSanPhamRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public boolean updateTrangThai(UUID id) {
        int updatedRecords = ctSanPhamRepository.updateTrangThai(id);
        return updatedRecords > 0;
    }

}
