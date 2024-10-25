package kr.co.exam.product.management.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Product {

    //Product와 ProductDto를 변환해줄때 사용하는 기본 의존성은 ModelMapper
    //따라서 pom.xml에 modelmapper 의존성을 추가해야함

    //modelMapper를 사용하고 있기 때문에 생성자를 통해서 유효성 검사를 할 수 없음
    //modelMapper는 파라미터가 없는 생성자를 사용해서 인스턴스를 생성하고, Reflexion을 통해 값을 설정하기 때문
    // > Bean Validation 을 활용한 유효성 검사를 함 (객체 필드에 어노테이션을 붙여 유효성 검사하는 방법)

    private Long id;

    @Size(min = 1, max = 100)
    private String name;

    @Max(1000000)
    @Min(0)
    private Integer price;

    @Max(9999)
    @Min(0)
    private Integer amount;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public Boolean containsName(String name) {
        return this.name.contains(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
