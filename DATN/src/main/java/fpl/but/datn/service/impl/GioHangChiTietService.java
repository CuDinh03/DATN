package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.GioHangChiTietDto;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    public GioHangChiTiet updateGioHangChiTietKH(UUID id, Integer newSoLuong) {
        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findById(id);

        if (optionalGioHangChiTiet.isPresent()) {
            GioHangChiTiet chiTietGioHang = optionalGioHangChiTiet.get();
            chiTietGioHang.setSoLuong(newSoLuong);

            if (newSoLuong == 0) {
                gioHangChiTietRepository.delete(chiTietGioHang);
            } else {
                gioHangChiTietRepository.save(chiTietGioHang);
            }
            return chiTietGioHang;
        } else {
            throw new RuntimeException("Gio Hang Chi Tiet không tồn tại với id " + id);
        }
    }

    @Override
    public List<Object[]> findAllChiTietAndHinhAnhByIdGioHang(UUID idGioHang) {

        return gioHangChiTietRepository.findAllChiTietAndHinhAnhByIdGioHang(idGioHang);
    }

    @Override
    public GioHangChiTiet addProductToGioHangKH(UUID idGioHang, UUID idSanPham, int soLuong) {
        // Tìm giỏ hàng theo idGioHang
        GioHang gioHang = gioHangRepository.findById(idGioHang)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        // Tìm chi tiết sản phẩm theo idSanPham
        ChiTietSanPham chiTietSanPham = ctSanPhamRepository.findById(idSanPham)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        if (chiTietSanPham.getSoLuong() < soLuong) {
            throw new RuntimeException("Số lượng sản phẩm không đủ");
        }

        // Tìm chi tiết giỏ hàng theo idGioHang và idSanPham
        Optional<GioHangChiTiet> existingChiTietGioHang = gioHangChiTietRepository.findByGioHangAndSanPhamChiTiet(gioHang, chiTietSanPham);

        GioHangChiTiet gioHangChiTiet;
        if (existingChiTietGioHang.isPresent()) {
            // Nếu đã tồn tại chi tiết giỏ hàng, cập nhật số lượng
            gioHangChiTiet = existingChiTietGioHang.get();
            gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + soLuong);
        } else {
            // Nếu không tồn tại, tạo mới chi tiết giỏ hàng
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHang);
            gioHangChiTiet.setNgaySua(new Date());
            gioHangChiTiet.setNgayTao(new Date());
            gioHangChiTiet.setChiTietSanPham(chiTietSanPham);
            gioHangChiTiet.setSoLuong(soLuong);
            gioHangChiTiet.setTrangThai(true);
        }

        // Cập nhật số lượng trong chi tiết sản phẩm
//        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - soLuong);
        ctSanPhamRepository.save(chiTietSanPham);

        // Lưu chi tiết giỏ hàng
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public GioHangChiTiet addProductToGioHang(UUID idGioHang, UUID idSanPham, int soLuong) {
        // Tìm giỏ hàng theo idGioHang
        GioHang gioHang = gioHangRepository.findById(idGioHang)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        // Tìm chi tiết sản phẩm theo idSanPham
        ChiTietSanPham chiTietSanPham = ctSanPhamRepository.findById(idSanPham)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        if (chiTietSanPham.getSoLuong() < soLuong) {
            throw new RuntimeException("Số lượng sản phẩm không đủ");
        }

        // Tìm chi tiết giỏ hàng theo idGioHang và idSanPham
        Optional<GioHangChiTiet> existingChiTietGioHang = gioHangChiTietRepository.findByGioHangAndSanPhamChiTiet(gioHang, chiTietSanPham);

        GioHangChiTiet gioHangChiTiet;
        if (existingChiTietGioHang.isPresent()) {
            // Nếu đã tồn tại chi tiết giỏ hàng, cập nhật số lượng
            gioHangChiTiet = existingChiTietGioHang.get();
            gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + soLuong);
        } else {
            // Nếu không tồn tại, tạo mới chi tiết giỏ hàng
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHang);
            gioHangChiTiet.setNgaySua(new Date());
            gioHangChiTiet.setNgayTao(new Date());
            gioHangChiTiet.setChiTietSanPham(chiTietSanPham);
            gioHangChiTiet.setSoLuong(soLuong);
            gioHangChiTiet.setTrangThai(true);
        }

        // Cập nhật số lượng trong chi tiết sản phẩm
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - soLuong);
        ctSanPhamRepository.save(chiTietSanPham);

        // Lưu chi tiết giỏ hàng
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }


}
