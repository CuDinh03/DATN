package fpl.but.datn.service.impl;

import fpl.but.datn.entity.GioHangHoaDon;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.GioHangHoaDonRepository;
import fpl.but.datn.repository.HoaDonChiTietRepository;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HoaDonService implements IHoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    private GioHangHoaDonRepository hoaDonGioHangRepository;
    @Override
    public List getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon create(HoaDon request) {
        HoaDon hoaDon = new HoaDon();
        Random random = new Random();
        hoaDon.setId(UUID.randomUUID());
        hoaDon.setMa("HD" + random.nextInt(1000));
        hoaDon.setVoucher(request.getVoucher());
        hoaDon.setNgaySua(new Date());
        hoaDon.setNgayTao(new Date());
        hoaDon.setTongTien(request.getTongTien());
        hoaDon.setNguoiDung(request.getNguoiDung());
        hoaDon.setKhachHang(request.getKhachHang());
        hoaDon.setTongTienGiam(request.getTongTienGiam());
        hoaDon.setGhiChu(request.getGhiChu());
        hoaDon.setVoucher(request.getVoucher());
        hoaDon.setTrangThai(1);
        return hoaDonRepository.save(hoaDon);
    }
    public HoaDon createHoaDonOnl(HoaDon request) {
        return hoaDonRepository.save(request);
    }

    @Override
    public HoaDon update(HoaDon request, UUID id) {
        HoaDon hoaDon = findById(id);
        hoaDon.setVoucher(request.getVoucher());
        hoaDon.setNgaySua(new Date());
        hoaDon.setNgayTao(new Date());
        hoaDon.setTongTien(request.getTongTien());
        hoaDon.setTongTienGiam(request.getTongTienGiam());
        hoaDon.setGhiChu(request.getGhiChu());
        hoaDon.setVoucher(request.getVoucher());
        hoaDon.setTrangThai(request.getTrangThai());
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public void delete(UUID id) {
        HoaDon hoaDon = findById(id);
        hoaDon.setTrangThai(1);
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public boolean xoaCungHoaDon(UUID id) {
        Optional<HoaDon> optional = hoaDonRepository.findById(id);

        if (optional.isPresent()) {
            List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findAllHoaDonChiTietByIdHoaDon(id);
            hoaDonChiTietRepository.deleteAll(hoaDonChiTiets);
            GioHangHoaDon gioHangHoaDon = hoaDonGioHangRepository.findByIdHoaDon(id);
            if (gioHangHoaDon != null) {
                hoaDonGioHangRepository.delete(gioHangHoaDon);
            }
            HoaDon hoaDon = optional.get();
            hoaDonRepository.delete(hoaDon);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public HoaDon updateTrangThai(UUID id, Integer trangThai) {
        HoaDon hoaDon = findById(id);
        hoaDon.setTrangThai(trangThai);
        return hoaDonRepository.save(hoaDon);
    }

    public void open(UUID id) {
    }
    @Override
    public HoaDon findById(UUID id) {
        return hoaDonRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
    }
    @Override
    public Page<HoaDon> getAllHoaDonPageable(Pageable pageable) {
        return hoaDonRepository.findAllPage(pageable);
    }

    @Override
    public Optional<HoaDon> findByMa(String ma) {
        return hoaDonRepository.findByMa(ma);
    }

    @Override
    public Page<HoaDon> getHoaDonsByTrangThai(Pageable pageable, Integer trangThai) {
        return hoaDonRepository.findByTrangThai(pageable, trangThai);
    }

    @Override
    public List<HoaDon> findHoaDonByKhachHang(UUID idKhachHang) {
        return hoaDonRepository.findHoaDonByKhachHang(idKhachHang);
    }

}
