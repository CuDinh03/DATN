package fpl.but.datn.dto.request;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaiKhoanDto {
    private UUID id;
    @Size(min = 6, max = 10, message = "USERNAME_INVALID")
    private String tenDangNhap;
    @Size(min = 8, max = 16, message = "PASSWORD_INVALID")
    private String matKhau;

}
