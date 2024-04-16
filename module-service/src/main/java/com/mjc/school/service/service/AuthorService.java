package com.mjc.school.service.service;

import com.mjc.school.repository.models.AuthorModel;
import com.mjc.school.service.GeneralService;
import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validate.GeneralValidation;
import com.mjc.school.service.validate.ValidationAuthor;
import com.mjc.school.repository.error.MyException;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.GeneralRepository;

import java.util.List;

public class AuthorService implements GeneralService<AuthorDTO> {

    GeneralRepository<AuthorModel> repository = new AuthorRepository();

    @Override
    public AuthorDTO create(AuthorDTO dto) {
        GeneralValidation<AuthorDTO> validation = new ValidationAuthor();
        try {
            validation.validate(dto);
            AuthorModel author = new AuthorModel();
            author.setName(dto.getName());

            if (repository.readAll().isEmpty()) author.setId(1L);
            else author.setId(repository.readAll().get(repository.readAll().size() - 1).getId() + 1);

            repository.create(author);
            return AuthorMapper.INSTANCE.authorToAuthorDto(author);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<AuthorDTO> getAll() {
        return AuthorMapper.INSTANCE.authorListToAuthorDtoList(repository.readAll());
    }

    @Override
    public AuthorDTO getById(Long id) {
        try {
            return AuthorMapper.INSTANCE.authorToAuthorDto(repository.readById(id));
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

            AuthorModel author = new AuthorModel();
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
