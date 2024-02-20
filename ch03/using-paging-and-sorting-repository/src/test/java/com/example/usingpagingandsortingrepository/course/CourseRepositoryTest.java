package com.example.usingpagingandsortingrepository.course;

import com.example.usingpagingandsortingrepository.model.Course;
import com.example.usingpagingandsortingrepository.repository.CourseRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void givenDataAvailableWhenLoadFirstPageThenGetFiveRecords() {
        Pageable pageable = PageRequest.of(0,3);
        assertThat(courseRepository.findAll(pageable)).hasSize(3);
        assertThat(pageable.getPageNumber()).isEqualTo(0);

        Pageable nextPageable = pageable.next();
        assertThat(courseRepository.findAll(nextPageable)).hasSize(1);
        assertThat(nextPageable.getPageNumber()).isEqualTo(1);
    }

    @Test
    public void givenDataAvailableWhenSortsFirstPageThenGetSortedSData() {
        // 이름순으로 오름차순 정렬
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("Name")));

        Condition<Course> sortedFirstCourseCondition = new Condition<>() {
            @Override
            public boolean matches(Course course) {
                return course.getId() == 4
                        && course.getName().equals("Cloud Native Spring Boot Application Development");
            }
        };

        assertThat(courseRepository.findAll(pageable)).first().has(sortedFirstCourseCondition);
    }

    @Test
    void givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
        // 평점순으로 내림차순, 이름순으로 오름차순 정렬
        Pageable customSortPageable = PageRequest.of(0,5, Sort.by("Rating").descending().and(Sort.by("Name")));
        Condition<Course> customSortFirstCourseCondition = new Condition<Course>() {
            @Override
            public boolean matches(Course course) {
                return course.getId() == 2 && course.getName().equals("Getting Started with Spring Security DSL");
            }
        };
        assertThat(courseRepository.findAll(customSortPageable)).first().has(customSortFirstCourseCondition);
    }
}
