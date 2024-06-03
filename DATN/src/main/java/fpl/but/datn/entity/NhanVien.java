package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    private String ten;
    @OneToOne
    private TaiKhoan taiKhoan;
    private String email;
    private String sdt;
    private Boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
