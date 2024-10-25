package kr.co.exam.product.management.presentation;

import jakarta.validation.Valid;
import kr.co.exam.product.management.application.SimpleProductService;
import kr.co.exam.product.management.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    @Autowired
    ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }

    //상품 추가 메서드
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return simpleProductService.add(productDto);
    }

    //아이디로 상품 검색 메서드
    @RequestMapping(value = "/products/{id}" , method = RequestMethod.GET)
    public ProductDto findProductById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }

    //전체 상품 가져오는 또는 이름으로 상품 검색 메서드
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> findProducts(@RequestParam(required = false) String name) {

        if(null == name) return simpleProductService.findAll();
        return simpleProductService.findByNameContaining(name);
    }

    //상품 수정 메서드
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return simpleProductService.update(productDto);
    }

    //상품 삭제 메서드
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }
}
