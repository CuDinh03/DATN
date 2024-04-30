package fpl.but.datn.dto;

import fpl.but.datn.entity.ChucVu;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data

public class TaiKhoanDto {
    private UUID id;

    private String ma;

    private String tenDangNhap;
    private String matKhau;
    private ChucVu idChucVu;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
