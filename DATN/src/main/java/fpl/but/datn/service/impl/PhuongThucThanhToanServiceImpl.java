package fpl.but.datn.service.impl;

import fpl.but.datn.entity.PhuongThucThanhToan;
import fpl.but.datn.repository.PhuongThucThanhToanRepository;
import fpl.but.datn.service.PhuongThucThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhuongThucThanhToanServiceImpl implements PhuongThucThanhToanService {

    @Autowired
    PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Override
    public List<PhuongThucThanhToan> getAll() {
        return phuongThucThanhToanRepository.findAll();
    }

    @Override
    public PhuongThucThanhToan getOneById(UUID id) {
        return phuongThucThanhToanRepository.getOne(id);
    }

    @Override
    public Boolean addPhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan) {
        phuongThucThanhToanRepository.save(phuongThucThanhToan);
        return true;
    }

    @Override
    public Boolean updatePhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan) {
        phuongThucThanhToanRepository.save(phuongThucThanhToan);
        return true;
    }

    @Override
    public Boolean deleteByIdPhuongThucThanhToan(UUID id) {
        phuongThucThanhToanRepository.deleteById(id);
        return true;
    }
}
