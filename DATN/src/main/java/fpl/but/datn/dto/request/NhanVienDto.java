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

public class NhanVienDto {
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
