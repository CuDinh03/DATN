package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public GioHangChiTiet create(GioHangChiTiet gioHangChiTiet) {

        return gioHangChiTietRepository.save(gioHangChiTiet);

    }

    @Override
    public GioHangChiTiet update(GioHangChiTiet gioHangChiTiet, UUID id) {
        return null;
    }


    @Override
    public GioHangChiTiet findById(UUID id) {
        return gioHangChiTietRepository.findById(id).get();
    }

    @Override
    public List<GioHangChiTiet> getAllByIdGioHang(UUID id) {
        return gioHangChiTietRepository.findAllByIdGioHang(id);
    }


    public GioHangChiTiet updateGioHangChiTiet(UUID id, Integer newSoLuong) {
        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findById(id);



        if (optionalGioHangChiTiet.isPresent()) {
            GioHangChiTiet chiTietGioHang = optionalGioHangChiTiet.get();
            ChiTietSanPham chiTietSanPham = chiTietGioHang.getChiTietSanPham();
            Integer oldSoLuong = chiTietGioHang.getSoLuong();

            if (newSoLuong > chiTietSanPham.getSoLuong() + oldSoLuong) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Số lượng nhập vào vượt quá số lượng sản phẩm chi tiết hiện có.");

            }
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