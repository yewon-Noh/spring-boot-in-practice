package com.example.crudrepository.repository;

import com.example.crudrepository.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    // 비어 있어도 된다.
}