package com.mjc.school.repository.impl;

import com.mjc.school.repository.error.ErrorCode;
import com.mjc.school.repository.error.MyException;
import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.repository.GeneralRepository;
import com.mjc.school.repository.source.DataSource;

import java.util.List;

public class NewsRepository implements GeneralRepository<NewsModel> {
    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public NewsModel create(NewsModel news) {
        dataSource.getNewsList().add(news);
        dataSource.writeNews();
        return news;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsList();
    }

    @Override
    public NewsModel readById(Long id) throws MyException {
        return dataSource.getNewsList().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MyException(ErrorCode.NO_SUCH_NEW.getErrorData()));
    }

    @Override
    public NewsModel update(NewsModel news, Long id) throws MyException {
        NewsModel returnNews = readById(id);

        returnNews.setTitle(news.getTitle());
        returnNews.setContent(news.getContent());
        returnNews.setAuthorId(news.getAuthorId());
        returnNews.setLastUpdateDate(news.getLastUpdateDate());
        dataSource.writeNews();

        return returnNews;
    }

    @Override
    public Boolean delete(Long id) {
        boolean result = dataSource.getNewsList().removeIf(news -> news.getId().equals(id));
        if (result) dataSource.writeNews();
        return result;
    }
}
