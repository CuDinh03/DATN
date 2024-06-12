package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private HoaDon hoaDon;
    @ManyToOne
    private ChiTietSanPham chiTietSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private BigDecimal giaBan;
    private Integer trangThai;
    @Override
    public String toString() {
        return "GioHangChiTiet{" +
                "id=" + id +
                ", hoaDon=" + hoaDon +
                ", chiTietSanPham=" + chiTietSanPham +
                ", soLuong=" + soLuong +
                ", ngayTao=" + ngayTao +
                ", ngaySua=" + ngaySua +
                ", giaBan=" + giaBan +
                ", trangThai=" + trangThai +
                '}';
    }
}
