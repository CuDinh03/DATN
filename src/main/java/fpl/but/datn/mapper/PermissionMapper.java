package fpl.but.datn.mapper;
import fpl.but.datn.dto.request.PermissionRequest;
import fpl.but.datn.dto.response.PermissionResponse;
import fpl.but.datn.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
