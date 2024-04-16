package service;

import dto.AuthorDTO;
import error.MyException;
import mapper.AuthorMapper;
import models.Author;
import repository.AuthorRepository;
import repository.GeneralRepository;
import validate.GeneralValidation;
import validate.ValidationAuthor;

import java.util.List;

public class AuthorService implements GeneralService<AuthorDTO> {

    GeneralRepository<Author> repository = new AuthorRepository();

    @Override
    public AuthorDTO create(AuthorDTO dto) throws MyException {
        GeneralValidation<AuthorDTO> validation = new ValidationAuthor();
        validation.validate(dto);
        Author author = new Author();
        author.setName(dto.getName());

        if (repository.getAll().isEmpty()) author.setId(1L);
        else author.setId(repository.getAll().get(repository.getAll().size() - 1).getId() + 1);

        return AuthorMapper.INSTANCE.authorToAuthorDto(author);
    }

    @Override
    public List<AuthorDTO> getAll() {
        return AuthorMapper.INSTANCE.authorListToAuthorDtoList(repository.getAll());
    }

    @Override
    public AuthorDTO getById(Long id) throws MyException {
        return AuthorMapper.INSTANCE.authorToAuthorDto(repository.getById(id));
    }

    @Override
    public AuthorDTO update(AuthorDTO dto, Long id) throws MyException {
        GeneralValidation<AuthorDTO> validation = new ValidationAuthor();
        validation.validate(dto);
        Author author = new Author();
        author.setName(dto.getName());

        return AuthorMapper.INSTANCE.authorToAuthorDto(repository.update(author, id));
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
