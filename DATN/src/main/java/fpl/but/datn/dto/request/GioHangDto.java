package fpl.but.datn.dto.request;

import fpl.but.datn.entity.KhachHang;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Date;
import java.util.UUID;

public class GioHangDto {
    private UUID id;
    private String ma;
    private KhachHang khachHang;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
