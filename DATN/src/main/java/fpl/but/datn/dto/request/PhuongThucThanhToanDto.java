package fpl.but.datn.dto.request;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PhuongThucThanhToanDto {
    private UUID id;
    private String ma;
    private String ten;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
