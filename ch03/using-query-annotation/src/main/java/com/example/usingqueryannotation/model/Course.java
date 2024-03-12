package com.example.usingqueryannotation.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "COURSES")
@NamedQueries({
        @NamedQuery(name = "Course.findAllByCategoryAndRating",
                query = "select c from Course c where c.category=?1 and c.rating=?2"),
        @NamedQuery(name = "Course.findAllByCategory",
                query = "select c from Course c where c.category=?1")
})
public class Course {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "DESCRIPTION")
    private String description;

    public Course(String name, String category, int rating, String description) {
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.description = description;
    }

    public Course() {

    }
}