package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.Branch;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.BranchAddDTO;
import com.example.appdbservice.payload.BranchInfoDTO;
import com.example.appdbservice.payload.BranchUpdateDTO;

import java.util.List;

public interface BranchService {


    ApiResult<List<BranchInfoDTO>> getAll(int page, int size);

    ApiResult<BranchInfoDTO> getOne(Long id);

    ApiResult<BranchInfoDTO> add(BranchAddDTO branchAddDTO);

    ApiResult<BranchInfoDTO> update(BranchUpdateDTO branchUpdateDTO, Long id);

    ApiResult<?> delete(Long id);

    Branch getByIdOrElseThrow(Long id);

}
