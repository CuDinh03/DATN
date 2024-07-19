package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.ThongTinDatHangDto;
import fpl.but.datn.entity.ThongTinDatHang;
import fpl.but.datn.repository.ThongTinDatHangRepo;
import fpl.but.datn.service.IThongTinDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ThongTinDatHangService implements IThongTinDatHangService {
    @Autowired
    private ThongTinDatHangRepo thongTinDatHangRepo;
    @Override
    public List getAll() {
        return thongTinDatHangRepo.findAll();
    }

    @Override
    public ThongTinDatHang create(ThongTinDatHang request) {
        ThongTinDatHang thongTinDatHang = new ThongTinDatHang();

        thongTinDatHang.setId(UUID.randomUUID());
        thongTinDatHang.setTen(request.getTen());
        thongTinDatHang.setNgayTao(new Date());
        thongTinDatHang.setNgaySua(new Date());
        thongTinDatHang.setSdt(request.getSdt());
        thongTinDatHang.setDiaChi(request.getDiaChi());
        thongTinDatHang.setKhachHang(request.getKhachHang());
        thongTinDatHang.setTrangThai(request.getTrangThai());
        return thongTinDatHangRepo.save(thongTinDatHang);
    }

    @Override
    public ThongTinDatHang update(ThongTinDatHang thongTinDatHang, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void open(UUID id) {

    }

    @Override
    public ThongTinDatHang findById(UUID id) {
        return null;
    }

    @Override
    public Page<ThongTinDatHang> getAllThongTinDatHangPageable(Pageable pageable) {
        return null;
    }

    @Override
    public List<ThongTinDatHang> getAllThongTinDatHangDangHoatDong() {
        return null;
    }

    @Override
    public ThongTinDatHang updateDm(ThongTinDatHang thongTinDatHang, UUID id) {
        return null;
    }

    @Override
    public List<ThongTinDatHang> getThongTinDatHangByIdKhachHang(UUID idKhachHang) {
        return thongTinDatHangRepo.findAllByKhachHang(idKhachHang);
    }
}
