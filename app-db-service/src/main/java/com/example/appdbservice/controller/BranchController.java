package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.BranchAddDTO;
import com.example.appdbservice.payload.BranchInfoDTO;
import com.example.appdbservice.payload.BranchUpdateDTO;
import com.example.appdbservice.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(AppConstant.BRANCH_CONTROLLER_PATH)
public interface BranchController {


    @GetMapping(AppConstant.VIEW)
    ApiResult<List<BranchInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @GetMapping(AppConstant.VIEW_ONE + "/{id}")
    ApiResult<BranchInfoDTO> getOne(@PathVariable Long id);

    @PostMapping(AppConstant.ADD)
    ApiResult<BranchInfoDTO> add(@RequestBody @Valid BranchAddDTO branchAddDTO);

    @PutMapping(AppConstant.UPDATE + "/{id}")
    ApiResult<BranchInfoDTO> update(@RequestBody BranchUpdateDTO branchUpdateDTO, @PathVariable Long id);

    @DeleteMapping(AppConstant.DELETE + "/{id}")
    ApiResult<?> delete(@PathVariable Long id);

}

