package mapper;

import com.mjc.school.service.dto.AuthorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.mjc.school.repository.models.Author;
import com.mjc.school.service.mapper.AuthorMapper;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T18:47:31+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
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

    @Override
    public List<AuthorDTO> authorListToAuthorDtoList(List<Author> authorList) {
        if ( authorList == null ) {
            return null;
        }

        List<AuthorDTO> list = new ArrayList<AuthorDTO>( authorList.size() );
        for ( Author author : authorList ) {
            list.add( authorToAuthorDto( author ) );
        }

        return list;
    }
}
