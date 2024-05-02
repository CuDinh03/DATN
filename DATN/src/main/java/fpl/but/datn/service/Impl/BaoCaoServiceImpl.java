package fpl.but.datn.service.Impl;

import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.repository.BaoCaoRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BaoCaoServiceImpl implements IService<BaoCao> {
    @Autowired
    private BaoCaoRepository baoCaoRepository;
    @Override
    public List<BaoCao> getAll() {
        return baoCaoRepository.findAll();
    }

    @Override
    public BaoCao addNew(BaoCao baoCao) {
        return baoCaoRepository.save(baoCao);
    }

    @Override
    public BaoCao update(BaoCao baoCao, UUID id) {
        Optional<BaoCao> optional = baoCaoRepository.findById(id);
        return optional.map(o -> {
            o.setMa(baoCao.getMa());
            o.setMoTa(baoCao.getMoTa());
            o.setTen(baoCao.getTen());
            o.setTrangThai(baoCao.getTrangThai());
            return baoCaoRepository.save(o);
        }).orElse(null);

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
        return baoCaoRepository.findById(id).get();
    }
}
