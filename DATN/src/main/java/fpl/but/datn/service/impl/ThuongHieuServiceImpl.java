package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ThuongHieu;
import fpl.but.datn.repository.ThuongHieuRepository;
import fpl.but.datn.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ThuongHieuServiceImpl implements InterfaceService<ThuongHieu> {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;


    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu addNew(ThuongHieu thuongHieu) {
        return thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public ThuongHieu update(ThuongHieu thuongHieu, UUID id) {
        Optional<ThuongHieu> optional = thuongHieuRepository.findById(id);
        return optional.map(o -> {
            o.setMa(thuongHieu.getMa());
            o.setTen(thuongHieu.getTen());
            o.setMoTa(thuongHieu.getMoTa());
            o.setNgayTao(thuongHieu.getNgayTao());
            o.setNgaySua(thuongHieu.getNgaySua());
            o.setTrangThai(thuongHieu.getTrangThai());
            return thuongHieuRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<ThuongHieu> optional = thuongHieuRepository.findById(id);
        if (optional.isPresent()){
            ThuongHieu thuongHieu = optional.get();
            thuongHieuRepository.delete(thuongHieu);
            return true;
        }
        return false;
    }

    @Override
    public ThuongHieu findById(UUID id) {
        return thuongHieuRepository.findById(id).get();
    }
}
