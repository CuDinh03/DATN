package fpl.but.datn.service.impl;

import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.PhuongThucThanhToan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.PhuongThucThanhToanRepository;
import fpl.but.datn.service.IPhuongThucThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhuongThucThanhToanService implements IPhuongThucThanhToanService {

    @Autowired
    PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Override
    public List<PhuongThucThanhToan> getAll() {
        return phuongThucThanhToanRepository.findAll();
    }

    @Override
    public PhuongThucThanhToan findById(UUID id) {
        return phuongThucThanhToanRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }

    @Override
    public PhuongThucThanhToan add(PhuongThucThanhToan request) {
        PhuongThucThanhToan phuongThucThanhToan1 = new PhuongThucThanhToan();

        if (phuongThucThanhToanRepository.existsByMa(request.getMa())) {
            throw new AppException(ErrorCode.MA_PTTHANHTOAN_TRUNG);
        }

        phuongThucThanhToan1.setId(UUID.randomUUID());
        phuongThucThanhToan1.setMa(request.getMa());
        phuongThucThanhToan1.setTen(request.getTen());
        phuongThucThanhToan1.setNgaySua(new Date());
        phuongThucThanhToan1.setNgayTao(new Date());
        phuongThucThanhToan1.setTrangThai(request.getTrangThai());

        return phuongThucThanhToanRepository.save(phuongThucThanhToan1);
    }

    @Override
    public PhuongThucThanhToan update(PhuongThucThanhToan request, UUID id) {
        PhuongThucThanhToan phuongThucThanhToan1 = new PhuongThucThanhToan();

        phuongThucThanhToan1.setId(id);
        phuongThucThanhToan1.setMa(request.getMa());
        phuongThucThanhToan1.setTen(request.getTen());
        phuongThucThanhToan1.setNgaySua(new Date());
        phuongThucThanhToan1.setNgayTao(request.getNgayTao());
        phuongThucThanhToan1.setTrangThai(request.getTrangThai());

        return phuongThucThanhToanRepository.save(phuongThucThanhToan1);
    }

    @Override
    public Boolean delete(UUID id) {
        Optional<PhuongThucThanhToan> optional = phuongThucThanhToanRepository.findById(id);
        if (optional.isPresent()){
            PhuongThucThanhToan phuongThucThanhToan = optional.get();
            phuongThucThanhToanRepository.delete(phuongThucThanhToan);
            return true;
        }
        return false;
    }
}
