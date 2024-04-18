package fpl.but.datn.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;

    private String ma;

    private String tenDangNhap;
    private String matKhau;
    @ManyToOne
    private ChucVu idChucVu;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
