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
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @Column(name = "loai_giam_gia")
    private String loaiGiamGia;
    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;
    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;
    @Column(name = "gia_tri_giam")
    private BigDecimal giamTriGiam;
    @Column(name = "gia_tri_toi_thieu")
    private BigDecimal giaTriToiThieu;
    @Column(name = "so_luong")
    private Integer soLuong;
    @Column(name = "trang_thai")
    private boolean trangThai;
}
