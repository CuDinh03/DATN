
package fpl.but.datn.dto.request;


import lombok.*;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ThuongHieuDto {

    private UUID id;

    private String ma;

    private String ten;

    private String moTa;

    private Date ngayTao;

    private Date ngaySua;

    private Boolean trangThai;
}
