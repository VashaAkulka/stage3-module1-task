package com.mjc.school.service.mapper;

import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.repository.models.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper( AuthorMapper.class );
    AuthorDTO authorToAuthorDto(Author author);
    List<AuthorDTO> authorListToAuthorDtoList(List<Author> authorList);

}
