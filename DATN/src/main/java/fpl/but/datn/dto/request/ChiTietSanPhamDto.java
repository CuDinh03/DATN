package fpl.but.datn.dto.request;

import fpl.but.datn.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
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
    private List<HinhAnh> HinhAnh;
}
