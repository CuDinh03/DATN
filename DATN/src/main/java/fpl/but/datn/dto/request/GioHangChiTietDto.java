package fpl.but.datn.dto.request;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;

import java.util.Date;
import java.util.UUID;

public class GioHangChiTietDto {
    private UUID id;
    private GioHang gioHang;
    private ChiTietSanPham chiTietSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;

}
