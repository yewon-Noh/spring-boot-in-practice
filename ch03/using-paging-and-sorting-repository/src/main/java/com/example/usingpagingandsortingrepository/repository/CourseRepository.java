package com.example.usingpagingandsortingrepository.repository;

import com.example.usingpagingandsortingrepository.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
