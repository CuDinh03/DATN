package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//Trang thai :
//    0: Chua thanh toan
//    1: Đang xác nhận
//    2: Đã xử lý
//    3: Dang giao
//    4: Da nhan hang
//    5: Hoàn thành
//    6: Da huy hang
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
    @OneToMany(mappedBy = "hoaDon")
    @JsonManagedReference
    private List<HoaDonChiTiet> hoaDonChiTietList;
}
