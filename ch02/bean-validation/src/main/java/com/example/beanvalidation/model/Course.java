package com.example.beanvalidation.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Course {

    private long id;
    private String name;
    private String category;

    @Min(value = 1,message = "A course should have a minimum of 1 rating")
    @Max(value = 5,message = "A course should have a maximum of 5 rating")
    private int rating;

    private String description;
}
