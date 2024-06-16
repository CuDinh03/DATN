package fpl.but.datn.service;

import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.entity.TaiKhoan;

public interface IKhachHangService {

    KhachHang getKhachHangBySdt(String sdt);
    KhachHang findKHByTenDangNhap(String tenDangNhap);

    KhachHang createWhenTk(TaiKhoan taiKhoan);
}
