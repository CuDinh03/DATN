package fpl.but.datn.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class VoucherDto {
    private UUID id;
    private String ma;
    private String ten;
    private String loaiGiamGia;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private BigDecimal giamTriGiam;
    private BigDecimal giaTriToiThieu;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
