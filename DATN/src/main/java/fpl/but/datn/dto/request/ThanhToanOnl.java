package fpl.but.datn.dto.request;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data

public class ThanhToanOnl {
    private GioHangDto gioHang;
    private BigDecimal tongTien;
    private BigDecimal tongTienGiam;
    private VoucherDto voucher;
    private String note;
    private List<GioHangChiTietDto> gioHangChiTietDtoList;
}
