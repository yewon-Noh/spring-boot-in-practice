package com.example.usingqueryannotation.course;

import com.example.usingqueryannotation.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Transactional
    public void givenCourseCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {
        assertThat(courseRepository.findAllByCategory("Spring")).hasSize(4);
        assertThat(courseRepository.findAllByRating(4)).hasSize(2);
        assertThat(courseRepository.findAllByCategoryAndRatingGreaterThan("Spring", 3)).hasSize(3);
        courseRepository.updateCourseRatingByName(3, "Cloud Native Spring Boot Application Development");
        assertThat(courseRepository.findAllByCategoryAndRatingGreaterThan("Spring", 4)).hasSize(1);
    }

}
