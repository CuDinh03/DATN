package fpl.but.datn.service.impl;

import fpl.but.datn.entity.Voucher;
import fpl.but.datn.repository.VoucherRepository;
import fpl.but.datn.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VoucherServiceImpl implements InterfaceService<Voucher> {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<Voucher> getAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher addNew(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher update(Voucher voucher, UUID id) {
        Optional<Voucher> optional = voucherRepository.findById(id);
        return optional.map(o -> {
            o.setMa(voucher.getMa());
            o.setTen(voucher.getTen());
            o.setLoaiGiamGia(voucher.getLoaiGiamGia());
            o.setNgayBatDau(voucher.getNgayBatDau());
            o.setNgayKetThuc(voucher.getNgayKetThuc());
            o.setGiaTriGiam(voucher.getGiaTriGiam());
            o.setGiaTriToiThieu(voucher.getGiaTriToiThieu());
            o.setSoLuong(voucher.getSoLuong());
            o.setNgayTao(voucher.getNgayTao());
            o.setNgaySua(voucher.getNgaySua());
            o.setTrangThai(voucher.isTrangThai());
            return voucherRepository.save(o);
        }).orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<Voucher> optional = voucherRepository.findById(id);
        if (optional.isPresent()){
            Voucher voucher = optional.get();
            voucherRepository.delete(voucher);
            return true;
        }
        return false;
    }

    @Override
    public Voucher findById(UUID id) {
        return voucherRepository.findById(id).get();
    }
}
