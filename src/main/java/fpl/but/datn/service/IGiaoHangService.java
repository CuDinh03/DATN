package fpl.but.datn.service;

import fpl.but.datn.entity.GiaoHang;

import java.util.UUID;

public interface IGiaoHangService {
    GiaoHang findByHoaDon_Id(UUID hoaDonId);

}
