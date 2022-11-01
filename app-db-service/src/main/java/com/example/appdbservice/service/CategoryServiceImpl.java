package com.example.appdbservice.service;

import com.example.appdbservice.entity.product.Category;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.mapper.CategoryMapper;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.CategoryAddDTO;
import com.example.appdbservice.payload.CategoryInfoDTO;
import com.example.appdbservice.payload.CategoryUpdateDTO;
import com.example.appdbservice.repository.product.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public ApiResult<List<CategoryInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryPage.getContent();
        List<CategoryInfoDTO> categoryInfoDTOS = categories
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(categoryInfoDTOS);
    }

    @Override
    public ApiResult<CategoryInfoDTO> getOne(Long id) {
        Category category = getByIdOrElseThrow(id);
        if (Objects.isNull(category)) {
            throw RestException.notFound("Category");
        }
        return ApiResult.successResponse(entityToInfoDTO(category));
    }

    @Override
    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
        checkName(categoryAddDTO.getNameRu(), categoryAddDTO.getNameUz());
        Category category = getByIdOrElseThrow(categoryAddDTO.getParentId());
        Category newCategory = new Category(
                category,
                categoryAddDTO.getDescription(),
                categoryAddDTO.getNameUz(),
                categoryAddDTO.getNameRu());
        categoryRepository.save(newCategory);
        return ApiResult.successResponse(entityToInfoDTO(newCategory));
    }

    @Override
    public ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Long id) {
        checkName(categoryUpdateDTO.getNameRu(), id);
        Category category = getByIdOrElseThrow(id);
        if (!categoryUpdateDTO.getDescription().isEmpty()) category.setDescription(categoryUpdateDTO.getDescription());
        if (!categoryUpdateDTO.getNameRu().isEmpty()) category.setRuName(categoryUpdateDTO.getNameRu());
        if (!categoryUpdateDTO.getNameUz().isEmpty()) category.setUzName(categoryUpdateDTO.getNameUz());
        Category parentCategory = getByIdOrElseThrow(categoryUpdateDTO.getParentId());
        category.setParentCategoryId(parentCategory);
        categoryRepository.save(category);
        return ApiResult.successResponse(entityToInfoDTO(category));
    }

    @Override
    public ApiResult<?> delete(Long id) {
        Category category = getByIdOrElseThrow(id);
        categoryRepository.delete(category);
        return ApiResult.successResponse("Successfully deleted!");
    }

    public Category getByIdOrElseThrow(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> RestException.notFound("Category"));
    }

    private CategoryInfoDTO entityToInfoDTO(Category category) {
        return categoryMapper.entityToInfoDTO(category);
    }


    private void checkName(String name, String name1) {
        boolean exists = categoryRepository.existsByRuNameOrUzNameEquals(name, name1);
        if (exists) throw RestException.alreadyExist("Category");
    }

    private void checkName(String name, Long id) {
        boolean exists = categoryRepository.existsByRuNameAndIdAndActiveTrue(name, id);
        if (exists) throw RestException.alreadyExist("Category");
    }


}
