package com.mjc.school.repository.models;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NewsModel implements Serializable {
    Long id;
    String title;
    String content;
    LocalDateTime createDate;
    LocalDateTime lastUpdateDate;
    Long authorId;
}
