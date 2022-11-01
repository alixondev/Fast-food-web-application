package com.example.appauthservice.controller;

import com.example.appauthservice.service.AuthService;
import com.example.appdbservice.payload.ApiResult;
import com.example.appauthservice.payload.RegisterDTO;
import com.example.appauthservice.payload.SignInDTO;
import com.example.appauthservice.payload.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {
        return authService.signUp(registerDTO);

    }
    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @Override
    public ApiResult<?> confirmAccount(Long userId, String verificationCode) {
        return authService.confirmAccount(userId,verificationCode);
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(TokenDTO tokenDTO) {
        return authService.refreshToken(tokenDTO);
    }


}
