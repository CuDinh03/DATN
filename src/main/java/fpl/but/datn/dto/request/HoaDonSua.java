package fpl.but.datn.dto.request;

import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.entity.NguoiDung;
import lombok.Data;

import java.util.List;

@Data
public class HoaDonSua {

    private List<HoaDonChiTiet> chiTietList;
    private HoaDon hoaDon;
    private NguoiDung nguoiDung;
}
