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
    public AuthorDTO create(AuthorDTO dto) {
        GeneralValidation<AuthorDTO> validation = new ValidationAuthor();
        try {
            validation.validate(dto);
            Author author = new Author();
            author.setName(dto.getName());

            if (repository.getAll().isEmpty()) author.setId(1L);
            else author.setId(repository.getAll().get(repository.getAll().size() - 1).getId() + 1);

            repository.save(author);
            return AuthorMapper.INSTANCE.authorToAuthorDto(author);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<AuthorDTO> getAll() {
        return AuthorMapper.INSTANCE.authorListToAuthorDtoList(repository.getAll());
    }

    @Override
    public AuthorDTO getById(Long id) {
        try {
            return AuthorMapper.INSTANCE.authorToAuthorDto(repository.getById(id));
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthorDTO update(AuthorDTO dto, Long id) {
        GeneralValidation<AuthorDTO> validation = new ValidationAuthor();
        try {
            validation.validate(dto);

            Author author = new Author();
            author.setName(dto.getName());

            return AuthorMapper.INSTANCE.authorToAuthorDto(repository.update(author, id));
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
