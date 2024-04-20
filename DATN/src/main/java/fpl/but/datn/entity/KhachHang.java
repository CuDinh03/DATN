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
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    private String ten;
    @OneToOne
    @JoinColumn(name = "id_tai_khoan")
    private TaiKhoan idTaiKhoan;
    private String email;
    private String sdt;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private boolean trangThai;
}
