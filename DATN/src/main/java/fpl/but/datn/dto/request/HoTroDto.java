package fpl.but.datn.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class HoTroDto {
    private UUID id;
    private String ma;
    private String tieuDe;
    private String noiDung;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
}
