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
@Table(name = "GioHang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    @OneToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang idKhachHang;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;
}
