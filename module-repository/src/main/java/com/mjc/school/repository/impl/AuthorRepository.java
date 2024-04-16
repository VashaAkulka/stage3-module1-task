package com.mjc.school.repository.impl;

import com.mjc.school.repository.error.ErrorCode;
import com.mjc.school.repository.error.MyException;
import com.mjc.school.repository.models.AuthorModel;
import com.mjc.school.repository.GeneralRepository;
import com.mjc.school.repository.source.DataSource;

import java.util.List;

public class AuthorRepository implements GeneralRepository<AuthorModel> {

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public void create(AuthorModel author) {
        dataSource.getAuthorList().add(author);
        dataSource.writeAuthors();
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthorList();
    }

    @Override
    public AuthorModel readById(Long id) throws MyException {
        return dataSource.getAuthorList().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MyException(ErrorCode.NO_SUCH_AUTHOR.getErrorData()));
    }

    @Override
    public AuthorModel update(AuthorModel author, Long id) throws MyException {
        AuthorModel returnAuthor = readById(id);
        returnAuthor.setName(author.getName());
        dataSource.writeAuthors();

        return returnAuthor;
    }

    @Override
    public Boolean delete(Long id) {
        boolean result = dataSource.getAuthorList().removeIf(author -> author.getId().equals(id));
        if (result) dataSource.writeAuthors();
        return result;
    }
}
