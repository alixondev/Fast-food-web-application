package com.example.appdbservice.service;

import com.example.appdbservice.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService{

    @Override
    public ApiResult<?> add(Long id) {
        return null;
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return null;
    }
}
