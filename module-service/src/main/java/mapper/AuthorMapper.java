package mapper;

import dto.AuthorDTO;
import models.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper( AuthorMapper.class );
    AuthorDTO authorToAuthorDto(Author author);
}
