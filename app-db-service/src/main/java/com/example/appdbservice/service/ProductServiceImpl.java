package com.example.appdbservice.service;

import com.example.appdbservice.entity.product.Category;
import com.example.appdbservice.entity.product.Product;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.ProductAddDTO;
import com.example.appdbservice.payload.ProductInfoDTO;
import com.example.appdbservice.payload.ProductUpdateDTO;
import com.example.appdbservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Override
    public ApiResult<List<ProductInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();
        List<ProductInfoDTO> productInfoDTOS =
                products
                        .stream()
                        .map(this::entityToInfoDTO)
                        .collect(Collectors.toList());
        return ApiResult.successResponse(productInfoDTOS);
    }

    @Override
    public ApiResult<ProductInfoDTO> getOne(Long id) {
        Product product = getByIdOrElseThrow(id);
        ProductInfoDTO productInfoDTO = entityToInfoDTO(product);
        return ApiResult.successResponse(productInfoDTO);
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        checkName(productAddDTO.getNameRu(),productAddDTO.getNameUZ());
        Category category = categoryService.getByIdOrElseThrow(productAddDTO.getCategoryId());
        Product product = new Product(
                productAddDTO.getNameUZ(),
                productAddDTO.getNameRu(),
                productAddDTO.getDescription(),
                productAddDTO.getAttachmentId(),
                productAddDTO.getPrice(),
                category
        );
        productRepository.save(product);
        return ApiResult.successResponse(entityToInfoDTO(product));
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Long id) {
        checkName(productUpdateDTO.getNameRu(),productUpdateDTO.getNameUZ(),id);
        Product product = getByIdOrElseThrow(id);
        if (!productUpdateDTO.getDescription().isEmpty()) product.setDescription(productUpdateDTO.getDescription());
        if (!productUpdateDTO.getNameRu().isEmpty()) product.setRuName(productUpdateDTO.getNameRu());
        if (!productUpdateDTO.getNameUZ().isEmpty()) product.setUzName(productUpdateDTO.getNameUZ());
        Category category = categoryService.getByIdOrElseThrow(productUpdateDTO.getCategoryId());
        product.setCategory(category);
        productRepository.save(product);
        return ApiResult.successResponse(entityToInfoDTO(product));
    }

    @Override
    public ApiResult<?> delete(Long id) {
        Product product = getByIdOrElseThrow(id);
        productRepository.delete(product);
        return ApiResult.successResponse("Success");
    }

    private ProductInfoDTO entityToInfoDTO(Product product) {
        return new ProductInfoDTO(
                product.getUzName(),
                product.getRuName(),
                product.getDescription(),
                product.getAttachment().getId(),
                product.getCategory().getId()
        );
    }

    @Override
    public Product getByIdOrElseThrow(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Product")
        );
    }

    private void checkName(String ruName, String uzName) {
        Optional<Product> productOptionalRu = productRepository.findByRuNameAndActiveTrue(ruName);
        if (productOptionalRu.isPresent()) throw RestException.alreadyExist("Product");
        Optional<Product> productOptionalUz = productRepository.findByUzNameAndActiveTrue(uzName);
        if (productOptionalUz.isPresent()) throw RestException.alreadyExist("Product");
    }

    private void checkName(String ruName, String uzName, Long id) {
        Optional<Product> optionalProductUz = productRepository.findByUzNameAndIdNotAndActiveTrue(uzName,id);
        if (optionalProductUz.isPresent()) throw RestException.alreadyExist("Product");
        Optional<Product> optionalProductRu = productRepository.findByRuNameAndIdNotAndActiveTrue(ruName,id);
        if (optionalProductRu.isPresent()) throw RestException.alreadyExist("Product");
    }

}
