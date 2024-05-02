package fpl.but.datn.service.Impl;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.repository.DanhMucRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CTSanPhamService implements IService<ChiTietSanPham> {

    @Autowired
    private CTSanPhamRepository cTSanPhamRepository;
    @Override
    public List<ChiTietSanPham> getAll() {
        return cTSanPhamRepository.findAll();
    }

    @Override
    public ChiTietSanPham addNew(ChiTietSanPham ctSanPham) {
        return cTSanPhamRepository.save(ctSanPham);
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham ctSanPham, UUID id) {
        Optional<ChiTietSanPham> optional = cTSanPhamRepository.findById(id);
        return optional.map(o -> {
            o.setMa(ctSanPham.getMa());
            o.setIdSanPham(ctSanPham.getIdSanPham());
            o.setIdChatLieu(ctSanPham.getIdChatLieu());
            o.setIdDanhMuc(ctSanPham.getIdDanhMuc());
            o.setIdHinhAnh(ctSanPham.getIdHinhAnh());
            o.setIdKichThuoc(ctSanPham.getIdKichThuoc());
            o.setIdMauSac(ctSanPham.getIdMauSac());
            o.setIdThuongHieu(ctSanPham.getIdThuongHieu());
            o.setGiaNhap(ctSanPham.getGiaNhap());
            o.setGiaBan(ctSanPham.getGiaBan());
            o.setSoLuong(ctSanPham.getSoLuong());
            o.setNgayNhap(ctSanPham.getNgayNhap());
            o.setTrangThai(ctSanPham.getTrangThai());
            return cTSanPhamRepository.save(o);
        }).orElse(null);

    }

    @Override
    public boolean delete(UUID id) {
        Optional<ChiTietSanPham> optional = cTSanPhamRepository.findById(id);
        if (optional.isPresent()){
            ChiTietSanPham ctSanPham = optional.get();
            cTSanPhamRepository.delete(ctSanPham);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public ChiTietSanPham findById(UUID id) {
        return cTSanPhamRepository.findById(id).get();
    }
}
