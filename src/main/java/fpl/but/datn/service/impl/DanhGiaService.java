package fpl.but.datn.service.impl;

import fpl.but.datn.entity.DanhGia;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.repository.DanhGiaRepository;
import fpl.but.datn.repository.HoaDonChiTietRepository;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IDanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class DanhGiaService implements IDanhGiaService {

    @Autowired
    private DanhGiaRepository danhGiaRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Override
    public List<DanhGia> getAll() {
        return danhGiaRepository.findAll();
    }

    @Override
    public DanhGia create(DanhGia request) {
        DanhGia danhGia = new DanhGia();
        Random random = new Random();

        danhGia.setId(UUID.randomUUID());
        danhGia.setMa("DG" + random.nextInt(1000));
        danhGia.setDiem(request.getDiem());
        danhGia.setTieuDe(request.getTieuDe());
        danhGia.setNoiDung(request.getNoiDung());
        danhGia.setKhachHang(request.getKhachHang());
        danhGia.setHoaDonChiTiet(request.getHoaDonChiTiet());
        danhGia.setNgayTao(new Date());
        danhGia.setNgaySua(new Date());
        danhGia.setTrangThai(request.getTrangThai());
        danhGia = danhGiaRepository.save(danhGia);
        request.getHoaDonChiTiet().setTrangThai(4);
        hoaDonChiTietRepository.save(request.getHoaDonChiTiet());

        return danhGia;
    }

    @Override
    public DanhGia update(DanhGia DanhGia, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void open(UUID id) {

    }

    @Override
    public DanhGia findById(UUID id) {
        return null;
    }

    @Override
    public Page<DanhGia> getAllDanhGiaPageable(Pageable pageable) {
        return null;
    }

    @Override
    public long countByChiTietSanPhamId(UUID productId) {
        return danhGiaRepository.countByChiTietSanPhamId(productId);
    }

    @Override
    public Double averageDiemByChiTietSanPhamId(UUID productId) {
        return danhGiaRepository.averageDiemByChiTietSanPhamId(productId);
    }
}
