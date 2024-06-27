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
    @JoinColumn(name = "san_pham_id", referencedColumnName = "id")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id", referencedColumnName = "id")
    private ThuongHieu thuongHieu;

    @ManyToOne
    @JoinColumn(name = "chat_lieu_id", referencedColumnName = "id")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "danh_muc_id", referencedColumnName = "id")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "kich_thuoc_id", referencedColumnName = "id")
    private KichThuoc kichThuoc;

    @ManyToOne
    @JoinColumn(name = "mau_sac_id", referencedColumnName = "id")
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
