package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    private String tenDangNhap;
    private String matKhau;
    @ManyToOne
    @JoinColumn(name = "idChucVu")
    private ChucVu chucVu;
    private boolean trangThai;

}
