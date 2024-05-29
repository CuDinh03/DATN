package fpl.but.datn.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class DanhMucDto {

    private UUID id;
    @NotBlank(message = "TEN KHONG DUOC DE TRONG")
    private String ten;
    @NotBlank(message = "MA KHONG DUOC DE TRONG")
    private String ma;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
