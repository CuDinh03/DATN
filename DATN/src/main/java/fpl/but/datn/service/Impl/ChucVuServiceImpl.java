package fpl.but.datn.service.Impl;

import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;
    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.findAll();
    }
}
