package fpl.but.datn.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChucVuResponse {
    private UUID id;
    private String ten;
}
