package com.example.springsecuritytest.repository;

import com.example.springsecuritytest.model.AuthoritiesEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthoritiesRepository extends CrudRepository<AuthoritiesEntity, String> {
    AuthoritiesEntity findByUsername(String username);
}
