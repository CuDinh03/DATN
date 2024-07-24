package fpl.but.datn.dto.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fpl.but.datn.entity.*;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChiTietSanPhamDto {
    private UUID id;
    private String ma;
    private SanPham sanPham;
    private ThuongHieu thuongHieu;
    private ChatLieu chatLieu;
    private DanhMuc danhMuc;
    private KichThuoc kichThuoc;
    private MauSac mauSac;
    private Integer soLuong;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private Date ngayNhap;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
    private List<HinhAnh> hinhAnh;
}
