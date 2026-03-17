package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.GioHangChiTietDto;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangRepository;
import fpl.but.datn.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return gioHangChiTietRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NO_CARTDETAIl_FOUND));
    }

    @Override
    public List<GioHangChiTiet> getAllByIdGioHang(UUID id) {
        return gioHangChiTietRepository.findAllByIdGioHang(id);
    }


    @Transactional
    public GioHangChiTiet updateGioHangChiTiet(UUID id, Integer newSoLuong) {
        GioHangChiTiet chiTietGioHang = gioHangChiTietRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NO_CARTDETAIl_FOUND));
        ChiTietSanPham chiTietSanPham = chiTietGioHang.getChiTietSanPham();

        if (newSoLuong > chiTietSanPham.getSoLuong()) {
            throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
        }
        if (newSoLuong == 0) {
            gioHangChiTietRepository.delete(chiTietGioHang);
            return chiTietGioHang;
        }
        chiTietGioHang.setSoLuong(newSoLuong);
        return gioHangChiTietRepository.save(chiTietGioHang);
    }


    @Transactional
    public GioHangChiTiet updateGioHangChiTietKH(UUID id, Integer newSoLuong) {
        GioHangChiTiet chiTietGioHang = gioHangChiTietRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NO_CARTDETAIl_FOUND));
        ChiTietSanPham ctsp = chiTietGioHang.getChiTietSanPham();
        if (newSoLuong > 0 && newSoLuong > ctsp.getSoLuong()) {
            throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
        }
        if (newSoLuong == 0) {
            gioHangChiTietRepository.delete(chiTietGioHang);
            return chiTietGioHang;
        }
        chiTietGioHang.setSoLuong(newSoLuong);
        return gioHangChiTietRepository.save(chiTietGioHang);
    }

    @Override
    public List<Object[]> findAllChiTietAndHinhAnhByIdGioHang(UUID idGioHang) {

        return gioHangChiTietRepository.findAllChiTietAndHinhAnhByIdGioHang(idGioHang);
    }

    @Override
    @Transactional
    public GioHangChiTiet addProductToGioHangKH(UUID idGioHang, UUID idSanPham, int soLuong) {
        // Tìm giỏ hàng theo idGioHang
        GioHang gioHang = gioHangRepository.findById(idGioHang)
                .orElseThrow(() -> new AppException(ErrorCode.GIO_HANG_NOT_FOUND));

        ChiTietSanPham chiTietSanPham = ctSanPhamRepository.findById(idSanPham)
                .orElseThrow(() -> new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND));

        if (chiTietSanPham.getSoLuong() < soLuong) {
            throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
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
            gioHangChiTiet.setTrangThai(1);
        }

        // Không trừ tồn kho khi thêm giỏ; chỉ trừ khi thanh toán/xác nhận đơn
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    @Transactional
    public GioHangChiTiet addProductToGioHang(UUID idGioHang, UUID idSanPham, int soLuong) {
        GioHang gioHang = gioHangRepository.findById(idGioHang)
                .orElseThrow(() -> new AppException(ErrorCode.GIO_HANG_NOT_FOUND));
        ChiTietSanPham chiTietSanPham = ctSanPhamRepository.findById(idSanPham)
                .orElseThrow(() -> new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND));

        if (chiTietSanPham.getSoLuong() < soLuong) {
            throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
        }

        Optional<GioHangChiTiet> existing = gioHangChiTietRepository.findByGioHangAndSanPhamChiTiet(gioHang, chiTietSanPham);
        GioHangChiTiet gioHangChiTiet;
        if (existing.isPresent()) {
            gioHangChiTiet = existing.get();
            int tongMoi = gioHangChiTiet.getSoLuong() + soLuong;
            if (chiTietSanPham.getSoLuong() < tongMoi) {
                throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
            }
            gioHangChiTiet.setSoLuong(tongMoi);
        } else {
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHang);
            gioHangChiTiet.setNgaySua(new Date());
            gioHangChiTiet.setNgayTao(new Date());
            gioHangChiTiet.setChiTietSanPham(chiTietSanPham);
            gioHangChiTiet.setSoLuong(soLuong);
            gioHangChiTiet.setTrangThai(1);
        }
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }


}
