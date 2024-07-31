package fpl.but.datn.mapper;

import fpl.but.datn.dto.request.ChucVuDto;
import fpl.but.datn.dto.response.ChucVuResponse;
import fpl.but.datn.entity.ChucVu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    ChucVu toRole(ChucVuDto request);

    ChucVuResponse toRoleResponse(ChucVu role);
}
