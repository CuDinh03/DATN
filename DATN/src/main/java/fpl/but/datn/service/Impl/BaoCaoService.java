package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.BaoCaoDto;
import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.BaoCaoRepository;
import fpl.but.datn.service.IBaoCaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BaoCaoService implements IBaoCaoService {
    @Autowired
    private BaoCaoRepository baoCaoRepository;
    @Override
    public List<BaoCao> getAll() {
        return baoCaoRepository.findAll();
    }

    @Override
    public BaoCao create(BaoCao request) {
        BaoCao baoCao = new BaoCao();

        if (baoCaoRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.REPORT_EXISTED);
        baoCao.setMa(request.getMa());
        baoCao.setTen(request.getTen());
        baoCao.setMoTa(request.getMoTa());
        baoCao.setId(UUID.randomUUID());
        baoCao.setNgayTao(new Date());
        baoCao.setNgaySua(new Date());
        baoCao.setTrangThai(request.getTrangThai());
        return baoCaoRepository.save(baoCao);
    }


    @Override
    public BaoCao update(BaoCao request, UUID id) {
        BaoCao baoCao = new BaoCao();
        baoCao.setTen(request.getTen());
        baoCao.setMa(request.getMa());
        baoCao.setMoTa(request.getMoTa());
        baoCao.setId(id);
        baoCao.setNgayTao(new Date());
        baoCao.setNgaySua(new Date());
        baoCao.setTrangThai(request.getTrangThai());
         return baoCaoRepository.save(baoCao);

    }

    @Override
    public boolean delete(UUID id) {
        Optional<BaoCao> optional = baoCaoRepository.findById(id);
        if (optional.isPresent()){
            BaoCao baoCao = optional.get();
            baoCaoRepository.delete(baoCao);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public BaoCao findById(UUID id) {
        return baoCaoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
    }

}
