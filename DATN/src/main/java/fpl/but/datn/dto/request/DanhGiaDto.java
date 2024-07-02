package fpl.but.datn.dto.request;

import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.entity.KhachHang;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class DanhGiaDto {

    private UUID id;
    private String ma;
    private KhachHang khachHang;
    private HoaDonChiTiet hoaDonChiTiet;
    private String tieuDe;
    private String noiDung;
    private Integer diem;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
}
