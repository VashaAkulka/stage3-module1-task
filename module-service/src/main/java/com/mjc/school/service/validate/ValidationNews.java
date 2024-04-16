package com.mjc.school.service.validate;

import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.repository.error.ErrorCode;
import com.mjc.school.repository.error.MyException;
import com.mjc.school.repository.models.Author;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.GeneralRepository;

public class ValidationNews implements GeneralValidation<NewsDTO> {

    public void validate(NewsDTO news) throws MyException {
        GeneralRepository<Author> repository = new AuthorRepository();

        String title = news.getTitle();
        String content = news.getContent();
        Long id = news.getAuthorId();

        if (title.isEmpty() || content.isEmpty()) throw new MyException(ErrorCode.EMPTY_FIELD.getErrorData());
        if (title.length() < 5 || title.length() > 30) throw new MyException(ErrorCode.FIELD_TITLE_INVALID_LENGTH.getErrorData());
        if (content.length() < 5 || content.length() > 255) throw new MyException(ErrorCode.FIELD_CONTENT_INVALID_LENGTH.getErrorData());
        repository.getById(id);
    }
}
