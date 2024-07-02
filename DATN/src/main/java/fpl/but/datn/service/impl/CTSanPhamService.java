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

import java.math.BigDecimal;
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
    public Page<ChiTietSanPham> getAllChiTietSanPhamPageableSapXepNGayTao(Pageable pageable) {
        return ctSanPhamRepository.findAllSapXepNgayTao(pageable);
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
            if (chiTietSanPham.getSoLuong()==null){
                chiTietSanPham.setSoLuong(0);
            }            if (chiTietSanPham.getGiaBan()==null){
                chiTietSanPham.setGiaBan(BigDecimal.valueOf(0));
            }            if (chiTietSanPham.getGiaNhap()==null){
                chiTietSanPham.setGiaNhap(BigDecimal.valueOf(0));
            }
            if (chiTietSanPham.getTrangThai() == 2){
                chiTietSanPham.setTrangThai(1);
            }

            // Cập nhật số lượng của sản phẩm chi tiết
            chiTietSanPham.setGiaNhap(request.getGiaNhap());
            chiTietSanPham.setGiaBan(request.getGiaBan());
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
            chiTietSanPham.setTrangThai(request.getTrangThai());
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
    @Transactional
    public boolean updateTrangThai(UUID id) {
        int updatedRecords = ctSanPhamRepository.updateTrangThai(id);
        return updatedRecords > 0;
    }

    public List<ChiTietSanPham> saveCtsp(SanPham sanPham,
                         List<MauSac> mauSacList,
                         ChatLieu chatLieu,
                         DanhMuc danhMuc,
                         ThuongHieu thuongHieu,
                         List<KichThuoc> kichThuocList){
        List<ChiTietSanPham> chiTietSanPhamsList = new ArrayList<>();
        for (MauSac ms : mauSacList){
            for (KichThuoc kt: kichThuocList){
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                Random random = new Random();
                chiTietSanPham.setMa("CTSP"+ random.nextInt(1000));
                chiTietSanPham.setChatLieu(chatLieu);
                chiTietSanPham.setThuongHieu(thuongHieu);
                chiTietSanPham.setDanhMuc(danhMuc);
                chiTietSanPham.setSanPham(sanPham);
                chiTietSanPham.setMauSac(ms);
                chiTietSanPham.setKichThuoc(kt);
                chiTietSanPham.setNgayNhap(new Date());
                chiTietSanPham.setNgayTao(new Date());
                chiTietSanPham.setNgaySua(new Date());
                chiTietSanPham.setTrangThai(2);
                chiTietSanPhamsList.add(create(chiTietSanPham));
            }
        }

        return chiTietSanPhamsList;

    }
    @Override
    public List<ChiTietSanPham> getCtsp(){
        return ctSanPhamRepository.getAllByTrangThai(2);
    }

    @Override
    public List<ChiTietSanPham> saveListCt(List<ChiTietSanPham> list) {
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
        for (ChiTietSanPham ct:
             list) {
            ct.setNgaySua(new Date());
            ct.setTrangThai(1);
            chiTietSanPhamList.add(update(ct,ct.getId()));
        }
        return chiTietSanPhamList;
    }


}
