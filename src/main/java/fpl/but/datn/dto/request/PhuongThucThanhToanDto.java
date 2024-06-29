package fpl.but.datn.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
public class PhuongThucThanhToanDto {
    private UUID id;
    private String ma;
    private String ten;
    private Date ngayTao;
    private Date ngaySua;
    private Integer code;
    private String moTa;
    private Integer trangThai;
}