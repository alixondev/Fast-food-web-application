package com.example.appauthservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appauthservice.payload.RegisterDTO;
import com.example.appauthservice.payload.SignInDTO;
import com.example.appauthservice.payload.TokenDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(AuthController.AUTHCONTROLLER_PATH)
public interface AuthController {
    String AUTHCONTROLLER_PATH = AppConstant.BASE_PATH + "/auth/";
    String SIGN_UP = "sign-up";
    String SIGN_IN = "sign-in";
    String CONFIRM_ACCOUNT = "confirm-account";
    String REFRESH_TOKEN = "refresh-token";


    @PostMapping(SIGN_UP)
    ApiResult<?> signUp(@RequestBody @Valid RegisterDTO registerDTO);

    @PostMapping(SIGN_IN)
    ApiResult<TokenDTO> signIn(@RequestBody @Valid SignInDTO signInDTO);

    @GetMapping(CONFIRM_ACCOUNT)
    ApiResult<?> confirmAccount(@RequestParam Long userId,
                                @RequestParam String verificationCode);

    @PostMapping(REFRESH_TOKEN)
    ApiResult<TokenDTO> refreshToken(@RequestBody TokenDTO tokenDTO);


}
