package fpl.but.datn.dto.request;

import fpl.but.datn.entity.TaiKhoan;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data

public class KhachHangDto {
    private UUID id;
    private String ma;
    private String ten;
    private TaiKhoan taiKhoan;
    private String email;
    private String sdt;
    private Boolean gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private Date ngaySua;
    private Date ngayTao;
    private Integer trangThai;
}
