package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HoTro;
import fpl.but.datn.service.IHoTroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoTroService implements IHoTroService {

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public HoTro create(HoTro hoTro) {
        return null;
    }

    @Override
    public HoTro update(HoTro hoTro, UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public HoTro findById(UUID id) {
        return null;
    }
}
