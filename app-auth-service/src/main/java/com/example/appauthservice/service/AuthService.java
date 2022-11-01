package com.example.appauthservice.service;

import com.example.appauthservice.payload.RegisterDTO;
import com.example.appauthservice.payload.SignInDTO;
import com.example.appauthservice.payload.TokenDTO;
import com.example.appdbservice.payload.ApiResult;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    ApiResult<?> signUp(RegisterDTO registerDTO);
    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);
    ApiResult<?> confirmAccount(Long userId, String verificationCode);
    ApiResult<TokenDTO> refreshToken(TokenDTO tokenDTO);
}
