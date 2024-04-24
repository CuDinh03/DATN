package fpl.but.datn.service.Impl;

import fpl.but.datn.entity.SanPham;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.repository.TaiKhoanRepository;
import fpl.but.datn.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaiKhoanServiceImpl implements InterfaceService<TaiKhoan> {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;


    @Override
    public List<TaiKhoan> getAll() {
        return taiKhoanRepository.findAll();
    }

    @Override
    public TaiKhoan addNew(TaiKhoan taiKhoan) {
        return taiKhoanRepository.save(taiKhoan);
    }

    @Override
    public TaiKhoan update(TaiKhoan taiKhoan, UUID id) {
        Optional<TaiKhoan> optional = taiKhoanRepository.findById(id);
        return optional.map(o -> {
            o.setMa(taiKhoan.getMa());
            o.setTenDangNhap(taiKhoan.getTenDangNhap());
            o.setMatKhau(taiKhoan.getMatKhau());
            o.setNgayTao(taiKhoan.getNgayTao());
            o.setNgaySua(taiKhoan.getNgaySua());
            o.setChucVu(taiKhoan.getChucVu());
            o.setTrangThai(taiKhoan.isTrangThai());
            return taiKhoanRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<TaiKhoan> optional = taiKhoanRepository.findById(id);
        if (optional.isPresent()){
            TaiKhoan taiKhoan = optional.get();
            taiKhoanRepository.delete(taiKhoan);
            return true;
        }
        return false;
    }

    @Override
    public TaiKhoan findById(UUID id) {
        return taiKhoanRepository.findById(id).get();
    }
}
