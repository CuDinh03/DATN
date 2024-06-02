package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class GioHangChiTietService implements IGioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private CTSanPhamRepository ctSanPhamRepository;
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public GioHangChiTiet create(GioHangChiTiet hoaDonGioHang) {
        return null;
    }

    @Override
    public GioHangChiTiet update(GioHangChiTiet hoaDonGioHang, UUID id) {
        return null;
    }

    @Override
    public GioHangChiTiet findById(UUID id) {
        return null;
    }

    @Override
    public List<GioHangChiTiet> getAllByIdGioHang(UUID id) {
        return gioHangChiTietRepository.findAllByIdGioHang(id);
    }


    public GioHangChiTiet updateGioHangChiTiet(UUID id, Integer newSoLuong) {
        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findById(id);

        if (optionalGioHangChiTiet.isPresent()) {
            GioHangChiTiet chiTietGioHang = optionalGioHangChiTiet.get();
            ChiTietSanPham chiTietSanPham = chiTietGioHang.getIdSanPham();
            Integer oldSoLuong = chiTietGioHang.getSoLuong();

            // Cập nhật số lượng trong GioHangChiTiet
            chiTietGioHang.setSoLuong(newSoLuong);

            if (newSoLuong == 0) {
                gioHangChiTietRepository.delete(chiTietGioHang);
                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + oldSoLuong);
            } else {
                gioHangChiTietRepository.save(chiTietGioHang);
                if (newSoLuong < oldSoLuong) {
                    chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + (oldSoLuong - newSoLuong));
                } else {
                    chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - (newSoLuong - oldSoLuong));
                }
            }

            ctSanPhamRepository.save(chiTietSanPham);
            return chiTietGioHang;
        } else {
            throw new RuntimeException("Gio Hang Chi Tiet không tồn tại với id " + id);
        }
    }



}
