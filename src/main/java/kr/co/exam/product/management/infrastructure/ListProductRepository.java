package kr.co.exam.product.management.infrastructure;

import kr.co.exam.product.management.domain.EntityNotFoundException;
import kr.co.exam.product.management.domain.Product;
import kr.co.exam.product.management.domain.ProductRepository;
import kr.co.exam.product.management.presentation.ProductDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class ListProductRepository implements ProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();
    //CopyOnWriteArrayList 를 사용한 이유 : 웹 애플리케이션이 여러개의 스레드가 동시에 동작하는 
    // 멀티 스레드라는 특수한 환경 때문에 '스레드 세이프한' 컬렉션을 사용해야 하기 때문

    private AtomicLong sequence = new AtomicLong(1L);
    //AtomicLong 도 스레드 안전성을 가지는 클래스로, Long 타입의 값을 안전하게 다룰수 있도록 만듦

    //상품 추가 메서드
    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }

    //아이디로 상품 검색 메서드
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product를 찾지 못했습니다."));
    }

    //전체 상품 가져오는 메서드
    public List<Product> findAll() {
        return products;
    }

    //이름으로 상품 검색 메서드
    public List<Product> findByNameContaining(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    //상품 수정 메서드
    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product);
        products.set(indexToModify, product);
        return product;
    }

    //상품 삭제 메서드
    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
