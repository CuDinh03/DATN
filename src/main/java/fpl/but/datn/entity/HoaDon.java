package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    @ManyToOne
    private NguoiDung nguoiDung;
    @ManyToOne
    private KhachHang khachHang;
    private BigDecimal tongTien;
    private BigDecimal tongTienGiam;
    private Date ngayTao;
    private Date ngaySua;
    @ManyToOne
    private Voucher voucher;
    private String ghiChu;
    private Integer trangThai;
}
