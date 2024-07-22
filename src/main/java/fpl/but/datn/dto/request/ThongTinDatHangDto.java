package fpl.but.datn.dto.request;

import fpl.but.datn.entity.KhachHang;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class ThongTinDatHangDto {
    private UUID id;
    private String ten;
    private String sdt;
    private String diaChi;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
    private KhachHang khachHang;
}
