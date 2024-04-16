package service;

import dto.NewsDTO;
import error.MyException;
import mapper.NewsMapper;
import models.News;
import repository.GeneralRepository;
import repository.NewsRepository;
import validate.GeneralValidation;
import validate.ValidationNews;

import java.time.LocalDateTime;
import java.util.List;

public class NewsService implements GeneralService<NewsDTO> {
    GeneralRepository<News> repository = new NewsRepository();

    @Override
    public NewsDTO create(NewsDTO newsDTO) {
        GeneralValidation<NewsDTO> validation = new ValidationNews();
        try {
            validation.validate(newsDTO);
            News news = new News();
            news.setTitle(newsDTO.getTitle());
            news.setContent(newsDTO.getContent());
            news.setCreateDate(LocalDateTime.now());
            news.setLastUpdateDate(LocalDateTime.now());
            news.setAuthorId(newsDTO.getAuthorId());

            if (repository.getAll().isEmpty()) news.setId(1L);
            else news.setId(repository.getAll().get(repository.getAll().size() - 1).getId() + 1);

            repository.save(news);
            return NewsMapper.INSTANCE.newsToNewsDto(news);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<NewsDTO> getAll() {
        return NewsMapper.INSTANCE.newsListToNewsDtoList(repository.getAll());
    }

    @Override
    public NewsDTO getById(Long id) {
        try {
            return NewsMapper.INSTANCE.newsToNewsDto(repository.getById(id));
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
            News news = new News();
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
