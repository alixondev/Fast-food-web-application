package com.example.appdbservice.service;

import com.example.appdbservice.entity.product.Category;
import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.entity.product.RecommendedProduct;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.RecommendedProductAddDTO;
import com.example.appdbservice.payload.RecommendedProductInfoDTO;
import com.example.appdbservice.repository.RecommendedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendedProductServiceImpl implements RecommendedProductService{

    private final RecommendedProductRepository recommendedProductRepository;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Override
    public ApiResult<RecommendedProductInfoDTO> getOne(Long id) {
        RecommendedProduct recommendedProduct = recommendedProductRepository.findById(id).orElseThrow(()
                -> RestException.notFound("Recommended Product"));
        return ApiResult.successResponse(entityToInfoDTO(recommendedProduct));
    }

    @Override
    public ApiResult<List<RecommendedProductInfoDTO>> getByCategoryId(Long id) {
        List<RecommendedProduct> recommendedProducts = recommendedProductRepository.findAllByCategoryId(id);
        return ApiResult.successResponse(recommendedProducts.stream().map(this::entityToInfoDTO).collect(Collectors.toList()));
    }

    @Override
    public ApiResult<List<RecommendedProductInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<RecommendedProduct> recommendedProductPage = recommendedProductRepository.findAll(pageable);
        List<RecommendedProduct> recommendedProducts = recommendedProductPage.getContent();
        return ApiResult.successResponse(recommendedProducts.stream().map(this::entityToInfoDTO).collect(Collectors.toList()));
    }

    @Override
    public ApiResult<RecommendedProductInfoDTO> add(RecommendedProductAddDTO recommendedProductAddDTO) {
        boolean existsByCategoryIdAndProductId = recommendedProductRepository.existsByCategoryIdAndProductId(
                recommendedProductAddDTO.getCategoryId(),
                recommendedProductAddDTO.getProductId()
        );
        if (existsByCategoryIdAndProductId) throw new RestException("Product is already connected to this category!", HttpStatus.CONFLICT);
        Category category = categoryService.getByIdOrElseThrow(recommendedProductAddDTO.getCategoryId());
        if (category.getParentCategoryId() == null) throw new RestException("Connect product to parent category is forbidden", HttpStatus.FORBIDDEN);
        Product product = productService.getByIdOrElseThrow(recommendedProductAddDTO.getProductId());
        RecommendedProduct recommendedProduct = new RecommendedProduct(
                category,
                product
        );
        recommendedProductRepository.save(recommendedProduct);

        return ApiResult.successResponse(entityToInfoDTO(recommendedProduct));
    }

    @Override
    public ApiResult<?> delete(Long id) {
        RecommendedProduct recommendedProduct = recommendedProductRepository.findById(id).orElseThrow(()
                -> RestException.notFound("Recommended Product"));
        recommendedProductRepository.delete(recommendedProduct);
        return ApiResult.successResponse("Recommended product deleted!");
    }

    private RecommendedProductInfoDTO entityToInfoDTO(RecommendedProduct recommendedProduct) {
        return  new RecommendedProductInfoDTO(
                recommendedProduct.getCategory().getId(),
                recommendedProduct.getProduct().getId()
        );
    }

}
