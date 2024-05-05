package fpl.but.datn.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaoCaoResponse {

    private String ma;
    private String moTa;
    private String ten;
}
