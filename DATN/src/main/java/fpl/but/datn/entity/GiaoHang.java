package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GiaoHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private KhachHang khachHang;

    @ManyToOne
    private HoaDon hoaDon;

    private String diaChiGiaoHang;
    private String phuongThucGiaoHang;
    private String donViVanChuyen;
    private Date ngayTao;
    private Date ngaySua;
    private Date ngayDuKienGiao;
    private Integer trangThai;
}
