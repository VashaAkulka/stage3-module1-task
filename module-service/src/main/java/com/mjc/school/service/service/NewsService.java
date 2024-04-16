package com.mjc.school.service.service;

import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.service.GeneralService;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validate.GeneralValidation;
import com.mjc.school.service.validate.ValidationNews;
import com.mjc.school.repository.error.MyException;
import com.mjc.school.repository.GeneralRepository;
import com.mjc.school.repository.impl.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;

public class NewsService implements GeneralService<NewsDTO> {
    GeneralRepository<NewsModel> repository = new NewsRepository();

    @Override
    public NewsDTO create(NewsDTO newsDTO) {
        GeneralValidation<NewsDTO> validation = new ValidationNews();
        try {
            validation.validate(newsDTO);
            NewsModel news = new NewsModel();
            news.setTitle(newsDTO.getTitle());
            news.setContent(newsDTO.getContent());
            news.setCreateDate(LocalDateTime.now());
            news.setLastUpdateDate(LocalDateTime.now());
            news.setAuthorId(newsDTO.getAuthorId());

            if (repository.readAll().isEmpty()) news.setId(1L);
            else news.setId(repository.readAll().get(repository.readAll().size() - 1).getId() + 1);

            repository.create(news);
            return NewsMapper.INSTANCE.newsToNewsDto(news);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<NewsDTO> getAll() {
        return NewsMapper.INSTANCE.newsListToNewsDtoList(repository.readAll());
    }

    @Override
    public NewsDTO getById(Long id) {
        try {
            return NewsMapper.INSTANCE.newsToNewsDto(repository.readById(id));
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO, Long id) {
        GeneralValidation<NewsDTO> validation = new ValidationNews();
        try {
            validation.validate(newsDTO);
            NewsModel news = new NewsModel();
            news.setTitle(newsDTO.getTitle());
            news.setContent(newsDTO.getContent());
            news.setAuthorId(newsDTO.getAuthorId());
            news.setLastUpdateDate(LocalDateTime.now());

            return NewsMapper.INSTANCE.newsToNewsDto(repository.update(news, id));
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
