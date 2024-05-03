package fpl.but.datn.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class SanPhamDto {
    private UUID id;
    private String ma;
    private String ten;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
