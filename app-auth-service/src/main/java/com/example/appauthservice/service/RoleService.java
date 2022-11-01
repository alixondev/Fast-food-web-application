package com.example.appauthservice.service;

import com.example.appauthservice.payload.*;
import com.example.appdbservice.entity.enums.Permissions;
import com.example.appdbservice.payload.ApiResult;

import java.util.List;
import java.util.Set;

public interface RoleService {
    ApiResult<RoleInfoDTO> getOne(Long id);

    ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO);

    ApiResult<?> delete(Long id);

    ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Long id);

    ApiResult<RoleInfoDTO> deletePerRole(List<Permissions> permissionEnums, Long id);

    ApiResult<RoleInfoDTO> addPerRole(Set<Permissions> permissionEnums, Long id);

    ApiResult<Set<Permissions>> getAllPermissions();

    ApiResult<List<RoleInfoDTO>> getAll();

    ApiResult<UserInfoDTO> addUserRole(UserUpdateDTO userUpdateDTO, Long id);
}
