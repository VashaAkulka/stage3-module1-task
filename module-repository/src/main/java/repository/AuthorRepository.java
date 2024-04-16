package repository;

import error.ErrorCode;
import error.MyException;
import models.Author;
import source.DataSource;

import java.util.List;

public class AuthorRepository implements GeneralRepository<Author> {

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public void save(Author author) {
        dataSource.getAuthorList().add(author);
    }

    @Override
    public List<Author> getAll() {
        return dataSource.getAuthorList();
    }

    @Override
    public Author getById(Long id) throws MyException {
        return dataSource.getAuthorList().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MyException(ErrorCode.NO_SUCH_AUTHOR.getErrorData()));
    }

    @Override
    public Author update(Author author, Long id) throws MyException {
        Author returnAuthor = getById(id);
        returnAuthor.setName(author.getName());

        return returnAuthor;
    }

    @Override
    public boolean delete(Long id) {
        return dataSource.getAuthorList().removeIf(author -> author.getId().equals(id));
    }
}
