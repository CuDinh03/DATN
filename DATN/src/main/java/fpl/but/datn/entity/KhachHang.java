package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private Boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private Date ngaySua;
    private Date ngayTao;
    private Boolean trangThai;


}

