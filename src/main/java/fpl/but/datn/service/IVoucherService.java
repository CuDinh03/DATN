package fpl.but.datn.service;

import fpl.but.datn.entity.Voucher;

import java.util.List;

public interface IVoucherService {
    Voucher findByMaGiam(String ma);

    Voucher findByTen(String ten);
}
