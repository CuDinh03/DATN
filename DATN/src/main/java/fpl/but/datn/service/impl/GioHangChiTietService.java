package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GioHangChiTietService implements IGioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private CTSanPhamRepository ctSanPhamRepository;
    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public List getAll() {
        return gioHangChiTietRepository.findAll();
    }

    @Override
    public GioHangChiTiet create(GioHangChiTiet request) {
        GioHangChiTiet ghct = new GioHangChiTiet();

//        ghct.setId(request.getId());
        ghct.setIdGioHang(request.getIdGioHang());
        ghct.setIdChiTietSanPham(request.getIdChiTietSanPham());
        ghct.setSoLuong(request.getSoLuong());
        ghct.setNgayTao(new Date());
        ghct.setNgaySua(new Date());
        ghct.setTrangThai(request.getTrangThai());

        return gioHangChiTietRepository.save(ghct);

    }

    @Override
    public GioHangChiTiet update(GioHangChiTiet gioHangChiTiet, UUID id) {
        Optional<GioHangChiTiet> optional = gioHangChiTietRepository.findById(id);
        return optional.map(o -> {
            o.setIdGioHang(gioHangChiTiet.getIdGioHang());
            o.setIdChiTietSanPham(gioHangChiTiet.getIdChiTietSanPham());
            o.setSoLuong(gioHangChiTiet.getSoLuong());
            o.setTrangThai(gioHangChiTiet.getTrangThai());
            return gioHangChiTietRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<GioHangChiTiet> optional = gioHangChiTietRepository.findById(id);
        if (optional.isPresent()){
            GioHangChiTiet gioHangChiTiet = optional.get();
            gioHangChiTietRepository.delete(gioHangChiTiet);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void open(UUID id) {
        GioHangChiTiet taiKhoan = findById(id);
        taiKhoan.setTrangThai(Boolean.TRUE);
        gioHangChiTietRepository.save(taiKhoan);
    }

    @Override
    public GioHangChiTiet findById(UUID id) {
        return gioHangChiTietRepository.findById(id).get();
    }

    @Override
    public Page<GioHangChiTiet> getAllGHCTPageable(Pageable pageable) {
        return gioHangChiTietRepository.findAll(pageable);
    }

//    public GioHangChiTiet updateGioHangChiTiet(UUID id, Integer newSoLuong) {
//        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findById(id);
//
//        if (optionalGioHangChiTiet.isPresent()) {
//            GioHangChiTiet chiTietGioHang = optionalGioHangChiTiet.get();
//            ChiTietSanPham chiTietSanPham = chiTietGioHang.getIdChiTietSanPham();
//            Integer oldSoLuong = chiTietGioHang.getSoLuong();
//
//            // Cập nhật số lượng trong GioHangChiTiet
//            chiTietGioHang.setSoLuong(newSoLuong);
//
//            if (newSoLuong == 0) {
//                gioHangChiTietRepository.delete(chiTietGioHang);
//                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + oldSoLuong);
//            } else {
//                gioHangChiTietRepository.save(chiTietGioHang);
//                if (newSoLuong < oldSoLuong) {
//                    chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + (oldSoLuong - newSoLuong));
//                } else {
//                    chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - (newSoLuong - oldSoLuong));
//                }
//            }
//
//            ctSanPhamRepository.save(chiTietSanPham);
//            return chiTietGioHang;
//        } else {
//            throw new RuntimeException("Gio Hang Chi Tiet không tồn tại với id " + id);
//        }
//    }

    public GioHangChiTiet themChiTietSanPham(UUID idGioHang,UUID idCTSanPham, Integer soLuong) {
        ChiTietSanPham chiTietSanPham = ctSanPhamRepository.findById(idCTSanPham).get();
        GioHang gioHang =  gioHangRepository.findById(idGioHang).get();
        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findByIdGioHangAndIdChiTietSanPham(idGioHang,idCTSanPham);

        if (chiTietSanPham.getSoLuong()< soLuong){
            throw new RuntimeException("Số lượng sản phẩm không đủ");

        }
        GioHangChiTiet gioHangChiTiet;
        if (optionalGioHangChiTiet.isPresent()){
            gioHangChiTiet = optionalGioHangChiTiet.get();
            gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong()+soLuong);
        }else {
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setIdGioHang(gioHang);
            gioHangChiTiet.setIdChiTietSanPham(chiTietSanPham);
            gioHangChiTiet.setSoLuong(soLuong);

        }
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong()-soLuong);
        ctSanPhamRepository.save(chiTietSanPham);
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

}
