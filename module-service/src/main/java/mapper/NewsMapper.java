package mapper;

import dto.NewsDTO;
import models.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper( NewsMapper.class );
    NewsDTO newsToNewsDto(News news);
    List<NewsDTO> newsListToNewsDtoList(List<News> newsList);
}
