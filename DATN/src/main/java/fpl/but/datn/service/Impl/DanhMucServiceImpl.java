package fpl.but.datn.service.Impl;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.repository.DanhMucRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DanhMucServiceImpl implements IService<DanhMuc> {

    @Autowired
    private DanhMucRepository danhMucRepository;
    @Override
    public List<DanhMuc> getAll() {
        return danhMucRepository.findAll();
    }

    @Override
    public DanhMuc addNew(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    @Override
    public DanhMuc update(DanhMuc danhMuc, UUID id) {
        Optional<DanhMuc> optional = danhMucRepository.findById(id);
        return optional.map(o -> {
            o.setMa(danhMuc.getMa());
            o.setMoTa(danhMuc.getMoTa());
            o.setTen(danhMuc.getTen());
            o.setTrangThai(danhMuc.isTrangThai());
            return danhMucRepository.save(o);
        }).orElse(null);

    }

    @Override
    public boolean delete(UUID id) {
        Optional<DanhMuc> optional = danhMucRepository.findById(id);
        if (optional.isPresent()){
            DanhMuc danhMuc = optional.get();
            danhMucRepository.delete(danhMuc);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public DanhMuc findById(UUID id) {
        return danhMucRepository.findById(id).get();
    }
}
