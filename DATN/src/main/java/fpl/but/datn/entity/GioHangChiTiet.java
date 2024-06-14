package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private GioHang gioHang;
    @ManyToOne
    private ChiTietSanPham chiTietSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
    @Override
    public String toString() {
        return "GioHangChiTiet{" +
                "id=" + id +
                ", gioHang=" + gioHang +
                ", chiTietSanPham=" + chiTietSanPham +
                ", soLuong=" + soLuong +
                ", ngayTao=" + ngayTao +
                ", ngaySua=" + ngaySua +
                ", trangThai=" + trangThai +
                '}';
    }
}
