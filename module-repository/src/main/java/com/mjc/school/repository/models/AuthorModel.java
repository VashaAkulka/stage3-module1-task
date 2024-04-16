package com.mjc.school.repository.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorModel implements Serializable {
    private Long id;
    private String name;
}
