package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.SanPham;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.service.ICTSanPhamService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class CTSanPhamService implements ICTSanPhamService {

    @Autowired
    private CTSanPhamRepository ctSanPhamRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return ctSanPhamRepository.findAll();
    }

    @Override
    public ChiTietSanPham create(ChiTietSanPham request) {

        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

        if (ctSanPhamRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.CTSP_EXISTED);

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
        chiTietSanPham.setTrangThai(1);
        chiTietSanPham.setHinhAnh(request.getHinhAnh());

        return ctSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham chiTietSanPham, UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public ChiTietSanPham findById(UUID id) {
        return ctSanPhamRepository.findById(id).get();
    }

    @Override
    public Page<ChiTietSanPham> getAllChiTietSanPhamPageable(Pageable pageable) {
        return ctSanPhamRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public boolean updateTrangThai(UUID id) {
        int updatedRecords = ctSanPhamRepository.updateTrangThai(id);
        return updatedRecords > 0;
    }

}
