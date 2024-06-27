package fpl.but.datn.dto.request;

import fpl.but.datn.entity.TaiKhoan;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
<<<<<<< HEAD:DATN/src/main/java/fpl/but/datn/entity/NhanVien.java
@Entity
@Table
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
=======

public class NhanVienDto {
>>>>>>> master:DATN/src/main/java/fpl/but/datn/dto/request/NhanVienDto.java
    private UUID id;
    private String ma;
    private String ten;
    private String email;
    private String sdt;
    private Boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
    private TaiKhoan idTaiKhoan;
}
