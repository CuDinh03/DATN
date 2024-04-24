package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @OneToOne
    @JoinColumn(name = "id_tai_khoan")
    private TaiKhoan idTaiKhoan;
    @Column(name = "email")
    private String email;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "gioi_tinh")
    private boolean gioiTinh;
    @Column(name = "ngay_sinh")
    private Date ngaySinh;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "trang_thai")
    private boolean trangThai;
}
