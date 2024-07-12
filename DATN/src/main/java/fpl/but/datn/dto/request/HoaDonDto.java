package fpl.but.datn.dto.request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.entity.NguoiDung;
import fpl.but.datn.entity.Voucher;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data

public class HoaDonDto {

    private UUID id;
    private String ma;
    private NguoiDung nguoiDung;
    private KhachHang khachHang;
    private BigDecimal tongTien;
    private BigDecimal tongTienGiam;
    private Date ngayTao;
    private Date ngaySua;
    private Voucher voucher;
    private String ghiChu;
    private Integer trangThai;
}
