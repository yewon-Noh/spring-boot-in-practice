package com.example.crudrepository.course;

import com.example.crudrepository.model.Course;
import com.example.crudrepository.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * course 객체를 생성해 DB에 저장한 후,
     * ID로 course 데이터를 조회해서 새로 생성한 course 객체의 정보와 DB 정보가 맞는지 확인
     */
    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
        Course course = new Course();
        course.setName("Rapid Spring Boot Application Development");
        course.setCategory("spring");
        course.setRating(4);
        course.setDescription("Spring Boot gives all the power of the Spring Framework without all of the complexities");

        Course savedCourse = courseRepository.save(course);

        assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
    }

    /**
     * course 객체를 새로 생성해서 DB에 저장한 후
     * courser 객체의 rating 필드값을 수정하고 저장하면 Db에서도 수정됬는지 확인
     */
    @Test
    public void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
        Course course = new Course();
        course.setName("Rapid Spring Boot Application Development");
        course.setCategory("spring");
        course.setRating(4);
        course.setDescription("Spring Boot gives all the power of the Spring Framework without all of the complexities");
        courseRepository.save(course);

        course.setRating(5);
        Course savedCourse = courseRepository.save(course);

        assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
    }

    /**
     * course 객체를 DB에 저장하고, 삭제한 후 조회가 되지 않는지 확인
     */
    @Test
    public void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
        Course course = new Course();
        course.setName("Rapid Spring Boot Application Development");
        course.setCategory("spring");
        course.setRating(4);
        course.setDescription("Spring Boot gives all the power of the Spring Framework without all of the complexities");
        Course savedCourse = courseRepository.save(course);

        assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);

        courseRepository.delete(course);
        assertThat(courseRepository.findById(savedCourse.getId()).isPresent()).isFalse();

    }

}
