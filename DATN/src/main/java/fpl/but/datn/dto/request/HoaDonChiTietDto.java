package fpl.but.datn.dto.request;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.HoaDon;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@Data
public class HoaDonChiTietDto {

    private UUID id;
    private HoaDon hoaDon;
    private ChiTietSanPham chiTietSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private BigDecimal giaBan;
    private Integer trangThai;
}
