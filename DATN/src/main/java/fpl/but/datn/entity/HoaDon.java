package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "ma")
    private String ma;
    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang idKhachHang;
    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private NguoiDung idNhanVien;
    @ManyToOne
    @JoinColumn(name = "id_voucher")
    private Voucher idVoucher;
    @Column(name = "tong_tien")

    private BigDecimal tongTien;
    @Column(name = "tong_tien_gian")
    private BigDecimal tongTienGiam;
    @Column(name = "ghi_chu")
    private String ghiChu;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @Column(name = "trang_thai")
    private boolean trangThai;
}
