package com.example.beanvalidation.validation;

import com.example.beanvalidation.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@Slf4j
@SpringBootTest
public class UserTest {

    @Test
    public void validateFalse() {
        User user = new User("sbip", "Sbip01$4UDfgggg");  // 비밀번호 정책 위반

        // 1. Validator 인스턴스 획득
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // 2. password 정책 확인
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // 3. 제약 위반 내용 출력
        violations.forEach(courseConstraintValidator ->
                log.error("A constraint violation has occurred. Violation details: [{}]", courseConstraintValidator)
        );
    }

    @Test
    public void validateTrue() {
        User user = new User("sbip", "Sbip01$4UDfg");

        // 1. Validator 인스턴스 획득
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // 2. password 정책 확인
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // 3. 제약 위반 내용 출력
        violations.forEach(courseConstraintValidator ->
                log.error("A constraint violation has occurred. Violation details: [{}]", courseConstraintValidator)
        );
    }
}
