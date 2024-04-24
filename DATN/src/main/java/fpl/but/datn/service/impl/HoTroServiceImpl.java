package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.entity.HoTro;
import fpl.but.datn.repository.HoTroRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class HoTroServiceImpl implements IService<HoTro> {
    @Autowired
    private HoTroRepository hoTroRepository;
    @Override
    public List<HoTro> getAll() {
        return hoTroRepository.findAll();
    }

    @Override
    public HoTro addNew(HoTro hoTro) {
        return hoTroRepository.save(hoTro);
    }

    @Override
    public HoTro update(HoTro hoTro, UUID id) {
        Optional<HoTro> optional = hoTroRepository.findById(id);
        return optional.map(o -> {
            o.setMa(hoTro.getMa());
            o.setTieuDe(hoTro.getTieuDe());
            o.setNoiDung(hoTro.getNoiDung());
            o.setTrangThai(hoTro.isTrangThai());
            return hoTroRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<HoTro> optional = hoTroRepository.findById(id);
        if (optional.isPresent()){
            HoTro hoTro = optional.get();
            hoTroRepository.delete(hoTro);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public HoTro findById(UUID id) {
        return hoTroRepository.findById(id).get();
    }
}
