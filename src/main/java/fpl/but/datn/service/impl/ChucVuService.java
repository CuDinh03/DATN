package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.ChucVuDto;
import fpl.but.datn.dto.response.ChucVuResponse;
import fpl.but.datn.mapper.RoleMapper;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChucVuService {
    ChucVuRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public ChucVuResponse create(ChucVuDto request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<ChucVuResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}