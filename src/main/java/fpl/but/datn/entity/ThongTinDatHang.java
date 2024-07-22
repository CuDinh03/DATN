package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ThongTinDatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ten;
    private String sdt;
    private String diaChi;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
    @ManyToOne
    private KhachHang khachHang;
}
