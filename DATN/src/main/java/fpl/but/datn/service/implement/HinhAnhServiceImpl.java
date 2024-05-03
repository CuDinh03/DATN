package fpl.but.datn.service.implement;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.repository.HinhAnhRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HinhAnhServiceImpl implements IService<HinhAnh> {
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Override
    public List<HinhAnh> getAll(){
        return hinhAnhRepository.findAll();
    }

    @Override
    public HinhAnh addNew(HinhAnh hinhAnh) {
        return hinhAnhRepository.save(hinhAnh);
    }

    @Override
    public HinhAnh update(HinhAnh hinhAnh, UUID id) {
        Optional<HinhAnh> optional = hinhAnhRepository.findById(id);
        return optional.map(o -> {
            o.setMa(hinhAnh.getMa());
            o.setMoTa(hinhAnh.getMoTa());
            o.setTen(hinhAnh.getTen());
            o.setTrangThai(hinhAnh.getTrangThai());
            return hinhAnhRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<HinhAnh> optional = hinhAnhRepository.findById(id);
        if (optional.isPresent()){
            HinhAnh hinhAnh = optional.get();
            hinhAnhRepository.delete(hinhAnh);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public HinhAnh findById(UUID id) {
        return hinhAnhRepository.findById(id).get();
    }
}
