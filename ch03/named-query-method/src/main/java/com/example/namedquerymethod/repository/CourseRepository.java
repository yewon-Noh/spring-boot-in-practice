package com.example.namedquerymethod.repository;

import com.example.namedquerymethod.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Iterable<Course> findAllByCategoryAndRating(String category, int rating);
    Iterable<Course> findAllByCategory(String category);
}
