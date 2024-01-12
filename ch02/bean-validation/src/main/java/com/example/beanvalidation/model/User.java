package com.example.beanvalidation.model;

import com.example.beanvalidation.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String name;

    @Password
    private String password;
}
