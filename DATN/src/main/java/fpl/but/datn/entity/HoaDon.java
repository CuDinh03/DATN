package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fpl.but.datn.listener.HoaDonListener;
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
//@EntityListeners(HoaDonListener.class)
//Trang thai :
//    0: Chua thanh toan
//    1: Chưa xác nhận
//    2: Đã xử lý
//    3: Dang giao
//    4: Hoàn thành
//    5: Da huy hang
//    6: Yêu cầu hủy đơn
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private Date ngayTao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private Date ngaySua;

    @ManyToOne
    private Voucher voucher;
    private String ghiChu;
    private Integer trangThai;
    @OneToMany(mappedBy = "hoaDon")
    @JsonManagedReference
    private List<HoaDonChiTiet> hoaDonChiTietList;

//    @Transient
//    private int ngaySuaChangeCount = 0;
//
//    public void incrementNgaySuaChangeCount() {
//        this.ngaySuaChangeCount++;
//    }

}
