package fpl.but.datn.dto.request;

import lombok.Data;

import java.util.Set;
@Data

public class ChucVuDto {
    String name;

    String description;

    Set<String> permissions;
}
