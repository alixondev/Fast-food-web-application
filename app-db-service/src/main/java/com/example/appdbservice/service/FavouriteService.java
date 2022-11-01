package com.example.appdbservice.service;


import com.example.appdbservice.payload.ApiResult;

public interface FavouriteService {

    ApiResult<?> add(Long id);

    ApiResult<?> delete(Long id);

}
