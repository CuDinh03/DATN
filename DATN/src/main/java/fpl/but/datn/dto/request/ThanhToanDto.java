
package fpl.but.datn.dto.request;

import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.PhuongThucThanhToan;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ThanhToanDto {

    private UUID id;

    private HoaDon idHoaDon;

    private PhuongThucThanhToan idPhuongThucThanhToan;

    private BigDecimal tienThanhToan;

    private Date ngayTao;

    private Date ngaySua;

    private Boolean trangThai;
}
