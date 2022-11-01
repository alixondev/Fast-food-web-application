package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.BranchAddDTO;
import com.example.appdbservice.payload.BranchInfoDTO;
import com.example.appdbservice.payload.BranchUpdateDTO;
import com.example.appdbservice.service.BranchService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class BranchControllerImpl implements BranchController{

    private final BranchService branchService;

    public BranchControllerImpl(BranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public ApiResult<List<BranchInfoDTO>> getAll(int page, int size) {
        return branchService.getAll(page,size);
    }

    @Override
    public ApiResult<BranchInfoDTO> getOne(Long id) {
        return branchService.getOne(id);
    }

    @Override
    public ApiResult<BranchInfoDTO> add(BranchAddDTO branchAddDTO) {
        return branchService.add(branchAddDTO);
    }

    @Override
    public ApiResult<BranchInfoDTO> update(BranchUpdateDTO branchUpdateDTO, Long id) {
        return branchService.update(branchUpdateDTO, id);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return branchService.delete(id);
    }
}
