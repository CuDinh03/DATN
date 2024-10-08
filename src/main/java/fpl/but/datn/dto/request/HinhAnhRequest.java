package fpl.but.datn.dto.request;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.SanPham;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class HinhAnhRequest {
    private UUID id;
    private String ma;
    private String url;
    private ChiTietSanPhamDto chiTietSanPham;
    private SanPham sanPham;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
}
