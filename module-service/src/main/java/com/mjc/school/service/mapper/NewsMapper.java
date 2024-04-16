package com.mjc.school.service.mapper;

import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.repository.models.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper( NewsMapper.class );
    NewsDTO newsToNewsDto(News news);
    List<NewsDTO> newsListToNewsDtoList(List<News> newsList);
}
