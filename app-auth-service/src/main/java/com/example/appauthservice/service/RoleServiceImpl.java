package com.example.appauthservice.service;

import com.example.appauthservice.payload.*;
import com.example.appdbservice.entity.enums.Permissions;
import com.example.appdbservice.entity.users.Role;
import com.example.appdbservice.entity.users.User;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appauthservice.repository.RoleRepository;
import com.example.appauthservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Override
    public ApiResult<RoleInfoDTO> getOne(Long id) {
        Role role = getByIdOrElseThrow(id);
        return ApiResult.successResponse(entityToInfoDTO(role));
    }

    @Override
    public ApiResult<List<RoleInfoDTO>> getAll() {
        return ApiResult.successResponse(roleRepository.
                findAll().
                stream().
                map(this::entityToInfoDTO).collect(Collectors.toList()));
    }

    @Override
    public ApiResult<UserInfoDTO> addUserRole(UserUpdateDTO userUpdateDTO, Long id) {
        User user = getUserByIdOrElseThrow(id);
        Role role = getByIdOrElseThrow(userUpdateDTO.getRoleId());
        user.setRole(role);
        userRepository.save(user);
//        return ApiResult.successResponse(entityToUserInfoDTO(user));
        return ApiResult.successResponse("Role userga biriktirildi");
    }

    @Override
    public ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO) {
        checkName(roleAddDTO.getName());
        Role role = new Role(
                roleAddDTO.getName(),
                roleAddDTO.getPermissions()
        );
        roleRepository.save(role);
        return ApiResult.successResponse(entityToInfoDTO(role));
    }

    @Override
    public ApiResult<?> delete(Long id) {
        try {
            roleRepository.deleteById(id);
            return ApiResult.successResponse("Role deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestException("Role not found!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Long id) {
        checkName(roleUpdateDTO.getName(), id);
        Role role = new Role(
                roleUpdateDTO.getName(),
                roleUpdateDTO.getPermissionEnums()
        );
        roleRepository.save(role);
        return ApiResult.successResponse(entityToInfoDTO(role));
    }

    @Override
    public ApiResult<RoleInfoDTO> deletePerRole(List<Permissions> permissionEnums, Long id) {
        Role role = getByIdOrElseThrow(id);
        Set<Permissions> permissions = role.getPermissions();
        permissions.removeAll(permissionEnums);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return ApiResult.successResponse(entityToInfoDTO(role));
    }

    @Override
    public ApiResult<RoleInfoDTO> addPerRole(Set<Permissions> permissionEnums, Long id) {
        Role role = getByIdOrElseThrow(id);
        role.setPermissions(permissionEnums);
        roleRepository.save(role);
        return ApiResult.successResponse(entityToInfoDTO(role));
    }

    @Override
    public ApiResult<Set<Permissions>> getAllPermissions() {
        Permissions[] permissionEnums = Permissions.values();
        return ApiResult.successResponse(permissionToSet(permissionEnums));
    }

    private RoleInfoDTO entityToInfoDTO(Role role) {
        return new RoleInfoDTO(
                role.getName(),
                role.getPermissions()
        );
    }

    private UserInfoDTO entityToUserInfoDTO(User user) {
        return new UserInfoDTO(
                user.getRole()
        );
    }

    private User getUserByIdOrElseThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> RestException.notFound("User"));
    }

    public static Set<Permissions> permissionToSet(Permissions[] values) {
        return new HashSet<>(Arrays.asList(values));
    }

    private void checkName(String name) {
        boolean exists = roleRepository.existsByNameAndDeletedFalse(name);
        if (exists) throw new RestException("Role is already exist", HttpStatus.CONFLICT);
    }

    private void checkName(String name, Long id) {
        boolean exists = roleRepository.existsByNameAndIdAndDeletedFalse(name, id);
        if (exists) throw RestException.alreadyExist("Role");
        roleRepository.findById(id).orElseThrow(() -> RestException.notFound("Role"));
    }

    public Role getByIdOrElseThrow(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(()
                -> RestException.notFound("Role"));
    }

//    private ApiResult<RoleInfoDTO> returnApiResult(Role role, boolean success, String msg) {
//        RoleInfoDTO roleDTO = entityToInfoDTO(role);
//        return new ApiResult<>(roleDTO, success, msg);
//    }


}
