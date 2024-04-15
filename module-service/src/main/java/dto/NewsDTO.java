package dto;

import lombok.Data;

@Data
public class NewsDTO {
    Long authorId;
    String title;
    String content;
}
