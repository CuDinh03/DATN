package fpl.but.datn.dto.request;

import fpl.but.datn.entity.ChucVu;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaiKhoanDto {
    private UUID id;
    private String ma;
    @Size(min = 6, max = 10, message = "USERNAME_INVALID")
    private String tenDangNhap;
    @Size(min = 8, max = 16, message = "PASSWORD_INVALID")
    private String matKhau;
    private ChucVu idChucVu;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
