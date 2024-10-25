package kr.co.exam.product.management.application;

import kr.co.exam.product.management.domain.Product;
import kr.co.exam.product.management.domain.ProductRepository;
import kr.co.exam.product.management.infrastructure.DatabaseProductRepository;
import kr.co.exam.product.management.infrastructure.ListProductRepository;
import kr.co.exam.product.management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    private ValidationService validationService;

    @Autowired
    SimpleProductService(ProductRepository productRepository, ModelMapper modelMapper
                        , ValidationService validationService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    //상품 추가 메서드
    public ProductDto add(ProductDto productDto) {

        //1. ProductDto를 Product로 변환하는 코드
        Product product = modelMapper.map(productDto, Product.class); //(변경하고싶은, 변경될)
        validationService.checkValid(product);

        //2. Repository를 호출하는 코드
        Product savedProduct = productRepository.add(product);

        //3. Product를 ProductDTO로 변환하는 코드
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        //4. DTO를 반환하는 코드
        return savedProductDto;
    }

    //아이디로 상품 검색 메서드
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    //전체 상품 가져오는 메서드
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    //이름으로 상품 검색 메서드
    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    //상품 수정 메서드
    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updatedProduct = productRepository.update(product);
        ProductDto updatedProductDto = modelMapper.map(updatedProduct, ProductDto.class);

        return updatedProductDto;
    }

    //상품 삭제 메서드
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
