package fpl.but.datn.dto.request;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.SanPham;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class GioHangChiTietDto {
    private UUID id;
    private GioHang idGioHang;
    private ChiTietSanPham idChiTietSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;

}
