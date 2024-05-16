package fpl.but.datn.service.impl;

import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.service.IHoaDonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class HoaDonService implements IHoaDonService {
    @Override
    public List getAll() {
        return null;
    }

    @Override
    public HoaDon create(HoaDon hoaDon) {
        return null;
    }

    @Override
    public HoaDon update(HoaDon hoaDon, UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public HoaDon findById(UUID id) {
        return null;
    }
}
