package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    @ManyToOne
    private SanPham sanPham;
    @ManyToOne
    private HinhAnh hinhAnh;
    @ManyToOne
    private ThuongHieu thuongHieu;
    @ManyToOne
    private ChatLieu chatLieu;
    @ManyToOne
    private DanhMuc danhMuc;
    @ManyToOne
    private KichThuoc kichThuoc;
    @ManyToOne
    private MauSac mauSac;
    private Integer soLuong;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private Date ngayNhap;
    private Date ngaySua;
    private Date ngayTao;
    private Boolean trangThai;
}
