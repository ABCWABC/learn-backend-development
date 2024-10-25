package kr.co.exam.product.management.application;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ValidationService {
    
    //유효성 검사를 위한 서비스 클래스
    //Product에 붙인 Bean Validation 애너테이션을 기준으로 이루어짐
    public <T> void checkValid(@Valid T validationTarget) {
        
    }
}
