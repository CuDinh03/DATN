package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    @ManyToOne
    private NguoiDung idNhanVien;
    @ManyToOne
    private KhachHang idKhachHang;
    private BigDecimal tongTien;
    private BigDecimal tongTienGiam;
    private Date ngayTao;
    private Date ngaySua;
    private UUID idVoucher;
    private String ghiChu;
    private boolean trangThai;
}
