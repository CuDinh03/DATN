package fpl.but.datn.dto;

import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.entity.NguoiDung;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@Data

public class HoaDonDto {

    private UUID id;
    private String ma;
    private NguoiDung idNhanVien;
    private KhachHang idKhachHang;
    private BigDecimal tongTien;
    private BigDecimal tongTienGiam;
    private Date ngayTao;
    private UUID idVoucher;
    private String ghiChu;
    private Boolean trangThai;
}
