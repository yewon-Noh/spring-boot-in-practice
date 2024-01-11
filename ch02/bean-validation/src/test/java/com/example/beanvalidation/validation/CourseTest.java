package com.example.beanvalidation.validation;

import com.example.beanvalidation.model.Course;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@Slf4j
@SpringBootTest
public class CourseTest {

    @Test
    public void validateTest() {
        Course course = new Course();
        course.setId(1);
        course.setRating(0);  // 최소값보다 작은 0으로 설정

        // 1. Validator 인스턴스 획득
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // 2. Course에 정의된 모든 제약 사항 준수 여부를 검증하고 위반 사항을 모아서 반환
        Set<ConstraintViolation<Course>> violations = validator.validate(course);

        // 3. 제약 위반 내용 출력
        violations.forEach(courseConstraintValidator ->
                log.error("A constraint violation has occurred. Violation details: [{}]", courseConstraintValidator)
        );
    }

    @Test
    public void validatePropertyTest() {
        Course course = new Course();
        course.setId(1);
        course.setRating(0);  // 최소값보다 작은 0으로 설정

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        // Course 객체의 'rating' 팔드에 대해 유효성 검증
        Set<ConstraintViolation<Course>> violation = validator.validateProperty(course, "rating");

        violation.forEach(courseConstraintValidator ->
                log.error("A constraint violation has occurred. Violation details: [{}]", courseConstraintValidator)
        );
    }
}
