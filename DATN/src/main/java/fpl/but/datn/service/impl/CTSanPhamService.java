package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.SanPham;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.service.ICTSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@Service
public class CTSanPhamService implements ICTSanPhamService {

    @Autowired
    private CTSanPhamRepository ctSanPhamRepository;

    @Override
    public List getAll() {
        return ctSanPhamRepository.findAll();
    }

    @Override
    public ChiTietSanPham create(ChiTietSanPham request) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        Random random = new Random();

        if (ctSanPhamRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        ctsp.setMa("CTSP" + random.nextInt(1000));
        ctsp.setIdSanPham(request.getIdSanPham());
        ctsp.setIdHinhAnh(request.getIdHinhAnh());
        ctsp.setIdThuongHieu(request.getIdThuongHieu());
        ctsp.setIdChatLieu(request.getIdChatLieu());
        ctsp.setIdDanhMuc(request.getIdDanhMuc());
        ctsp.setIdKichThuoc(request.getIdKichThuoc());
        ctsp.setIdMauSac(request.getIdMauSac());
        ctsp.setSoLuong(request.getSoLuong());
        ctsp.setGiaNhap(request.getGiaNhap());
        ctsp.setGiaBan(request.getGiaBan());
        ctsp.setNgayNhap(request.getNgayNhap());
        ctsp.setNgayTao(new Date());
        ctsp.setNgaySua(new Date());
        ctsp.setTrangThai(request.getTrangThai());

        return ctSanPhamRepository.save(ctsp);

    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham request, UUID id) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setId(id);
        ctsp.setMa(request.getMa());
        ctsp.setIdSanPham(request.getIdSanPham());
        ctsp.setIdHinhAnh(request.getIdHinhAnh());
        ctsp.setIdThuongHieu(request.getIdThuongHieu());
        ctsp.setIdChatLieu(request.getIdChatLieu());
        ctsp.setIdDanhMuc(request.getIdDanhMuc());
        ctsp.setIdKichThuoc(request.getIdKichThuoc());
        ctsp.setIdMauSac(request.getIdMauSac());
        ctsp.setSoLuong(request.getSoLuong());
        ctsp.setGiaNhap(request.getGiaNhap());
        ctsp.setGiaBan(request.getGiaBan());
        ctsp.setNgayNhap(request.getNgayNhap());
        ctsp.setNgayTao(new Date());
        ctsp.setNgaySua(new Date());
        ctsp.setTrangThai(request.getTrangThai());

        return ctSanPhamRepository.save(ctsp);

    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public void open(UUID id) {
            ChiTietSanPham taiKhoan = findById(id);
            taiKhoan.setTrangThai(Boolean.TRUE);
            ctSanPhamRepository.save(taiKhoan);

    }

    @Override
    public ChiTietSanPham findById(UUID id) {
        return null;
    }

    @Override
    public Page<ChiTietSanPham> getAllCTSanPhamPageable(Pageable pageable) {
        return ctSanPhamRepository.findAll(pageable);
    }
}
