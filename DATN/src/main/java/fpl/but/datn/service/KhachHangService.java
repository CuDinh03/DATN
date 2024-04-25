package fpl.but.datn.service;

import fpl.but.datn.entity.KhachHang;

import java.util.List;
import java.util.UUID;

public interface KhachHangService {

    List<KhachHang> getAll();

    KhachHang getOneById(UUID id);

    Boolean addKhachHang(KhachHang khachHang);

    Boolean updateKhachHang(KhachHang khachHang);

    Boolean deleteByIdKhachHang(UUID id);
}
