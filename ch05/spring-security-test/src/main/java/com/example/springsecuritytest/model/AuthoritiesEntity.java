package com.example.springsecuritytest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthoritiesEntity {
    @Id
    private String username;
    private String authority;
}
