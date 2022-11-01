package com.example.appauthservice.controller;

import com.example.appauthservice.payload.*;
import com.example.appdbservice.entity.enums.Permissions;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping(AppConstant.ROLE_CONTROLLER_PATH)
public interface RoleController {

//    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping(AppConstant.VIEW_ONE+"/{id}")
    ApiResult<RoleInfoDTO> getOne(@PathVariable Long id);

//    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping(AppConstant.VIEW)
    ApiResult<List<RoleInfoDTO>> getAll();

//    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping(AppConstant.ADD)
    ApiResult<RoleInfoDTO> add(@RequestBody RoleAddDTO roleAddDTO);

//    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping(AppConstant.DELETE + "/{id}")
    ApiResult<?> delete(@PathVariable Long id);

//    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @PutMapping(AppConstant.UPDATE + "/{id}")
    ApiResult<RoleInfoDTO> update(@RequestBody RoleUpdateDTO roleUpdateDTO, @PathVariable Long id);

//    @PreAuthorize(value = "hasAuthority('DELETE_PERMISSIONS_ROLE')")
    @DeleteMapping(AppConstant.DELETE_PERMISSIONS_ROLE+"/{id}")
    ApiResult<RoleInfoDTO> deletePerRole(@RequestBody List<Permissions> permissionEnums, @PathVariable Long id);

//    @PreAuthorize(value = "hasAuthority('ADD_PERMISSIONS_ROLE')")
    @PostMapping(AppConstant.ADD_PERMISSIONS_ROLE + "/{id}")
    ApiResult<RoleInfoDTO> addPerRole(@RequestBody Set<Permissions> permissionEnums, @PathVariable Long id);

    @GetMapping(AppConstant.PERMISSIONS)
    ApiResult<Set<Permissions>> getAllPermission();

//    @PreAuthorize(value = "hasAuthority('MANAGE_ROLE_TO_USER')")
    @PostMapping(AppConstant.ADD_USER_ROLE + "/{id}")
    ApiResult<UserInfoDTO> roleUser(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id);


}
