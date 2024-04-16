package com.mjc.school.repository.impl;

import com.mjc.school.repository.error.ErrorCode;
import com.mjc.school.repository.error.MyException;
import com.mjc.school.repository.models.News;
import com.mjc.school.repository.GeneralRepository;
import com.mjc.school.repository.source.DataSource;

import java.util.List;

public class NewsRepository implements GeneralRepository<News> {
    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public void save(News news) {
        dataSource.getNewsList().add(news);
        dataSource.writeNews();
    }

    @Override
    public List<News> getAll() {
        return dataSource.getNewsList();
    }

    @Override
    public News getById(Long id) throws MyException {
        return dataSource.getNewsList().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MyException(ErrorCode.NO_SUCH_NEW.getErrorData()));
    }

    @Override
    public News update(News news, Long id) throws MyException {
        News returnNews = getById(id);

        returnNews.setTitle(news.getTitle());
        returnNews.setContent(news.getContent());
        returnNews.setAuthorId(news.getAuthorId());
        returnNews.setLastUpdateDate(news.getLastUpdateDate());
        dataSource.writeNews();

        return returnNews;
    }

    @Override
    public boolean delete(Long id) {
        boolean result = dataSource.getNewsList().removeIf(news -> news.getId().equals(id));
        if (result) dataSource.writeNews();
        return result;
    }
}
