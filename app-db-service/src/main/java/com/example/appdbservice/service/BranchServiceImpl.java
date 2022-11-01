package com.example.appdbservice.service;

import com.example.appdbservice.entity.order.Branch;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.BranchAddDTO;
import com.example.appdbservice.payload.BranchInfoDTO;
import com.example.appdbservice.payload.BranchUpdateDTO;
import com.example.appdbservice.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService{
    private final BranchRepository branchRepository;

    @Override
    public ApiResult<List<BranchInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Branch> branchPage = branchRepository.findAll(pageable);
        List<Branch> branches = branchPage.getContent();
        List<BranchInfoDTO> branchInfoDTOS =
                branches
                        .stream()
                        .map(this::entityInfoDTO)
                        .collect(Collectors.toList());

        return ApiResult.successResponse(branchInfoDTOS);
    }

    @Override
    public ApiResult<BranchInfoDTO> getOne(Long id) {
       Branch branch = getByIdOrElseThrow(id);
       BranchInfoDTO branchInfoDTO = entityInfoDTO(branch);
       return ApiResult.successResponse(branchInfoDTO);

    }

    @Override
    public ApiResult<BranchInfoDTO> add(BranchAddDTO branchAddDTO) {
        checkName(branchAddDTO.getAddress());
        Branch branch = new Branch(
                branchAddDTO.getAddress(),
                branchAddDTO.getStartTime(),
                branchAddDTO.getEndTime(),
                branchAddDTO.getBranchPhoneNumber()
        );
        branchRepository.save(branch);
        return ApiResult.successResponse(entityInfoDTO(branch));
    }

    @Override
    public ApiResult<BranchInfoDTO> update(BranchUpdateDTO branchUpdateDTO, Long id) {
        checkName(branchUpdateDTO.getAddress());
        Branch branch = getByIdOrElseThrow(id);
        branch.setAddress(branchUpdateDTO.getAddress());
        branch.setStartWorkingTime((Date) branchUpdateDTO.getStartTime());
        branch.setEndWorkingTime((Date) branchUpdateDTO.getEndTime());
        branch.setBranchPhoneNumber(branchUpdateDTO.getBranchPhoneNumber());
        branchRepository.save(branch);
        return ApiResult.successResponse(entityInfoDTO(branch));
    }

    @Override
    public ApiResult<?> delete(Long id) {
        Branch branch = getByIdOrElseThrow(id);
        branchRepository.delete(branch);
        return ApiResult.successResponse("Success");
    }

    private BranchInfoDTO entityInfoDTO(Branch branch) {
        return new BranchInfoDTO(
                branch.getStartWorkingTime(),
                branch.getEndWorkingTime(),
                branch.getAddress(),
                branch.getBranchPhoneNumber()

        );
    }
    public Branch getByIdOrElseThrow(Long id) {
        return branchRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Branch")
        );
    }

    private void checkName(String address) {
        Optional<Branch> branchOptional = branchRepository.findByAddress(address);
        if (branchOptional.isPresent()) throw RestException.alreadyExist("Branch");

    }

}
