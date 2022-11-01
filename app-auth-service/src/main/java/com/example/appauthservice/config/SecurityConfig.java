package com.example.appauthservice.config;

import com.example.appauthservice.security.JwtFilter;
import com.example.appauthservice.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(@Lazy PasswordEncoder passwordEncoder,
                          @Lazy AuthService authService,
                          @Lazy JwtFilter jwtFilter) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/api/auth/**",
                        "/api/"


                ).permitAll()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/",
                        "/favicon.ico",
                        "//*.png",
                        "//*.gif",
                        "//*.svg",
                        "//*.jpg",
                        "//*.html",
                        "//*.css",
                        "//*.js",
                        "/swagger-ui.html",
                        "/swagger-resources/",
                        "/v2/",
                        "/csrf",
                        "/webjars/"
                ).permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        ;

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
