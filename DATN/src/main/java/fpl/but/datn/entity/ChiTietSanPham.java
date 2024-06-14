package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
    private ThuongHieu thuongHieu;
    @ManyToOne
    private ChatLieu chatLieu;
    @ManyToOne
    private DanhMuc danhMuc;
    @ManyToOne
    private KichThuoc kichThuoc;
    @ManyToOne
    private MauSac mauSac;
    @JsonIgnore
    @OneToMany(mappedBy = "chiTietSanPham", fetch = FetchType.EAGER)
    private List<HinhAnh> hinhAnh;
    private Integer soLuong;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private Date ngayNhap;
    private Date ngaySua;
    private Date ngayTao;
    private Integer trangThai;



}
