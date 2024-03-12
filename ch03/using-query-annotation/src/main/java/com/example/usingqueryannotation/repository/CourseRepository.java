package com.example.usingqueryannotation.repository;

import com.example.usingqueryannotation.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("select c from Course c where c.category=?1")
    Iterable<Course> findAllByCategory(String category);

    @Query("select c from Course c where c.category=:category and c.rating > :rating")
    Iterable<Course> findAllByCategoryAndRatingGreaterThan(@Param("category") String category, @Param("rating") int rating);

    @Query(value = "select * from COURSES where rating=?1", nativeQuery = true)
    Iterable<Course> findAllByRating(int rating);

    @Modifying
    @Transactional
    @Query("update Course c set c.rating=:rating where c.name=:name")
    int updateCourseRatingByName(@Param("rating") int rating, @Param("name") String name);

}
