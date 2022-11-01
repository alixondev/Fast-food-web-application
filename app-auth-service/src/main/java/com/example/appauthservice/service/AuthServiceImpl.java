package com.example.appauthservice.service;

import com.example.appauthservice.payload.RegisterDTO;
import com.example.appauthservice.payload.SignInDTO;
import com.example.appauthservice.payload.TokenDTO;
import com.example.appauthservice.repository.RoleRepository;
import com.example.appauthservice.repository.UserRepository;
import com.example.appauthservice.security.JWTProvider;
import com.example.appdbservice.entity.users.Role;
import com.example.appdbservice.entity.users.User;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.utils.AppConstant;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {
        checkPhoneNumber(registerDTO.getPhoneNumber());
        Role role = roleRepository.findByName(AppConstant.USER_ROLE).orElseThrow(()
                -> RestException.notFound("Role"));
        User user = new User(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getPhoneNumber(),
                registerDTO.getAddressId(),
                registerDTO.getAvatarId(),
                passwordEncoder.encode(registerDTO.getPassword()),
                registerDTO.getLanguage(),
                true,
                role
        );
        userRepository.save(user);
        String accessToken = jwtProvider.generateTokenFromId(user.getId(), true);
        String refreshToken = jwtProvider.generateTokenFromId(user.getId(), false);
        return ApiResult.successResponse(new TokenDTO(accessToken, refreshToken));
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInDTO.getUsername(),
                            signInDTO.getPassword()
                    )
            );
            User user = (User) authenticate.getPrincipal();
            String accessToken = jwtProvider.generateTokenFromId(user.getId(), false);
            String refreshToken = jwtProvider.generateTokenFromId(user.getId(), true);
            TokenDTO tokenDTO = new TokenDTO(accessToken, refreshToken);
            return ApiResult.successResponse(tokenDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.unauthorization("Password or Username is wrong");
        }
    }

    @Override
    public ApiResult<?> confirmAccount(Long userId, String verificationCode) {
        User user = getUserByIdOrElseThrow(userId);
//        if (!Objects.equals(verificationCode, user.getVerificationCode()))
//            throw RestException.restThrow("Wrong code");
//        user.setEnabled(true);
//        userRepository.save(user);
//        return ApiResult.successResponse("Successfully confirmed");
        return null;
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(TokenDTO tokenDTO) {
        try {
            jwtProvider.validateToken(tokenDTO.getAccessToken());
            return ApiResult.successResponse(tokenDTO);
        } catch (ExpiredJwtException e) {
            try {
                jwtProvider.validateToken(tokenDTO.getRefreshToken());
                Long userId = Long.valueOf(jwtProvider.getIdFromToken(tokenDTO.getRefreshToken()));
                return ApiResult.successResponse(new TokenDTO(
                        jwtProvider.generateTokenFromId(userId, true),
                        jwtProvider.generateTokenFromId(userId, false)
                ));
            } catch (Exception ex) {
                throw new RestException("Refresh token buzilgan", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new RestException("Access token buzligan", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return (UserDetails) userRepository.findByPhoneNumber(username).orElseThrow(() -> RestException.notFound("User"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void checkPhoneNumber(String phoneNumber) {
        boolean exist = userRepository.existsByPhoneNumber(phoneNumber);
        if (exist) throw RestException.alreadyExist("This phone number is");
    }

    private User getUserByIdOrElseThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> RestException.notFound("User"));
    }


    //    private void sendPhoneNumber(User user) {
//        twilioService.sendVerificationCode(
//                user.getPhoneNumber(),
//                user.getVerificationCode()
//        );
//    }

    private String generateVerificationCode() {
        String code = String.valueOf((int) (Math.random() * 1_000_000_000));
        return code.substring(0, 6);
    }

}
