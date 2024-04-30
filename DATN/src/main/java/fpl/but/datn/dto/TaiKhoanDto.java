package fpl.but.datn.dto;

import fpl.but.datn.entity.ChucVu;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data

public class TaiKhoanDto {
    private UUID id;

    private String ma;
    @Size(min = 6, max = 10, message = "Ten dang nhap phai tu 6 den 10 ky tu")
    private String tenDangNhap;
    @Size(min = 8, max = 16, message = "Mat khau phai tu 8 den 16 ky tu")
    private String matKhau;
    private ChucVu idChucVu;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
