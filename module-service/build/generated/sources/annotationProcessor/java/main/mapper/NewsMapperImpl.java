package mapper;

import dto.NewsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import models.News;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T18:47:31+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTO newsToNewsDto(News news) {
        if ( news == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setAuthorId( news.getAuthorId() );
        newsDTO.setTitle( news.getTitle() );
        newsDTO.setContent( news.getContent() );

        return newsDTO;
    }

    @Override
    public List<NewsDTO> newsListToNewsDtoList(List<News> newsList) {
        if ( newsList == null ) {
            return null;
        }

        List<NewsDTO> list = new ArrayList<NewsDTO>( newsList.size() );
        for ( News news : newsList ) {
            list.add( newsToNewsDto( news ) );
        }

        return list;
    }
}
