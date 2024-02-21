package com.example.namedquerymethod.course;

import com.example.namedquerymethod.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void givenCoursesCreatedWhenLoadCoursesBySpringCategoryThenExpectThreeCourses() {
        assertThat(courseRepository.findAllByCategoryAndRating("Spring", 4)).hasSize(2);
    }
}
