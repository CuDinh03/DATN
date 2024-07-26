package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")

    private Date ngayTao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")

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
