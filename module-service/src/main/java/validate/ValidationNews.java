package validate;

import dto.NewsDTO;
import error.ErrorCode;
import error.MyException;
import models.Author;
import repository.AuthorRepository;
import repository.GeneralRepository;

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
