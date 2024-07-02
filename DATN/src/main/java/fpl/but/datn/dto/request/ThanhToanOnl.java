package fpl.but.datn.dto.request;


import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.Voucher;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Data
@Getter
@Setter
public class ThanhToanOnl {
    private GioHang gioHang;
    private BigDecimal tongTien;
    private BigDecimal tongTienGiam;
    private Voucher voucher;
    private String diaChiGiaoHang;
    private String note;
    private List<GioHangChiTiet> gioHangChiTietList;
}
