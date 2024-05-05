package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ThanhToan;
import fpl.but.datn.repository.ThanhToanRepository;
import fpl.but.datn.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ThanhToanServiceImpl implements InterfaceService<ThanhToan> {

    @Autowired
    private ThanhToanRepository thanhToanRepository;


    @Override
    public List<ThanhToan> getAll() {
        return thanhToanRepository.findAll();
    }

    @Override
    public ThanhToan addNew(ThanhToan thanhToan) {
        return thanhToanRepository.save(thanhToan);
    }

    @Override
    public ThanhToan update(ThanhToan thanhToan, UUID id) {
        Optional<ThanhToan> optional = thanhToanRepository.findById(id);
        return optional.map(o -> {
            o.setIdHoaDon(thanhToan.getIdHoaDon());
            o.setIdPhuongThucThanhToan(thanhToan.getIdPhuongThucThanhToan());
            o.setTienThanhToan(thanhToan.getTienThanhToan());
            o.setNgayTao(thanhToan.getNgayTao());
            o.setNgaySua(thanhToan.getNgaySua());
            o.setTrangThai(thanhToan.getTrangThai());
            return thanhToanRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<ThanhToan> optional = thanhToanRepository.findById(id);
        if (optional.isPresent()){
            ThanhToan thanhToan = optional.get();
            thanhToanRepository.delete(thanhToan);
            return true;
        }
        return false;
    }

    @Override
    public ThanhToan findById(UUID id) {
        return thanhToanRepository.findById(id).get();
    }
}
