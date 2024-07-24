package fpl.but.datn.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.SanPham;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HinhAnhDto {
    private UUID id;
    private String ma;
    private String url;
    private ChiTietSanPham chiTietSanPham;
    private SanPham sanPham;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
}
