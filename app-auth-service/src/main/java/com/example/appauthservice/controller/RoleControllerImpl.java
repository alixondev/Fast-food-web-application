package com.example.appauthservice.controller;

import com.example.appauthservice.payload.*;
import com.example.appauthservice.service.RoleService;
import com.example.appdbservice.entity.enums.Permissions;
import com.example.appdbservice.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    public ApiResult<RoleInfoDTO> getOne(Long id) {
        return roleService.getOne(id);
    }

    @Override
    public ApiResult<List<RoleInfoDTO>> getAll() {
        return roleService.getAll();
    }

    @Override
    public ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO) {
        return roleService.add(roleAddDTO);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return roleService.delete(id);
    }


    @Override
    public ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Long id) {
        return roleService.update(roleUpdateDTO,id);
    }

    @Override
    public ApiResult<RoleInfoDTO> deletePerRole(List<Permissions> permissionEnums, Long id) {
        return roleService.deletePerRole(permissionEnums,id);
    }

    @Override
    public ApiResult<RoleInfoDTO> addPerRole(Set<Permissions> permissionEnums, Long id) {
        return roleService.addPerRole(permissionEnums,id);
    }

    @Override
    public ApiResult<Set<Permissions>> getAllPermission() {
        return roleService.getAllPermissions();
    }

    @Override
    public ApiResult<UserInfoDTO> roleUser(UserUpdateDTO userUpdateDTO, Long id) {
        return roleService.addUserRole(userUpdateDTO,id);
    }
}
