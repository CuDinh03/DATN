package fpl.but.datn.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    private String tenDangNhap;
    private String matKhau;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
    @ManyToMany
    private Set<ChucVu> chucVus;
}