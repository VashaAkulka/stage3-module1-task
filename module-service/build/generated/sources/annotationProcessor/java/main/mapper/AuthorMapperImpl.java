package mapper;

import dto.AuthorDTO;
import javax.annotation.processing.Generated;
import models.Author;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T14:58:49+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 11.0.20 (Oracle Corporation)"
)
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDTO authorToAuthorDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setName( author.getName() );

        return authorDTO;
    }
}
