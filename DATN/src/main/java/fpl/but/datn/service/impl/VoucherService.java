package fpl.but.datn.service.impl;

import fpl.but.datn.entity.Voucher;
import fpl.but.datn.service.IService;
import fpl.but.datn.service.IVoucherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class VoucherService implements IService<Voucher> , IVoucherService {
    @Override
    public Voucher getByID(UUID id) {
        return null;
    }

    @Override
    public Voucher createAccount(Voucher voucher) {
        return null;
    }

    @Override
    public Voucher update(UUID uuid, Voucher voucher) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<Voucher> getAll() {
        return null;
    }

    @Override
    public Page<Voucher> getAllTaiKhoanPageable(Pageable pageable) {
        return null;
    }

    @Override
    public Voucher findByMaGiam(String ma) {
        return null;
    }
}
