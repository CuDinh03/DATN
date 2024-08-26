package fpl.but.datn.service.impl;

import fpl.but.datn.entity.GiaoHang;
import fpl.but.datn.repository.GiaoHangRepository;
import fpl.but.datn.service.IGiaoHangService;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GiaoHangService implements IService<GiaoHang>, IGiaoHangService {

    @Autowired
    private GiaoHangRepository repository;
    @Override
    public GiaoHang getByID(UUID id) {
        return null;
    }

    @Override
    public GiaoHang create(GiaoHang giaoHang) {
        return repository.save(giaoHang);
    }

    @Override
    public GiaoHang update(UUID uuid, GiaoHang giaoHang) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<GiaoHang> getAll() {
        return null;
    }

    @Override
    public Page<GiaoHang> getAllPageable(Pageable pageable) {
        return null;
    }

    @Override
    public GiaoHang findByHoaDon_Id(UUID hoaDonId) {
        return repository.findByHoaDon_Id(hoaDonId);
    }
}
