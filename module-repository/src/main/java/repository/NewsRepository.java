package repository;

import dto.NewsDTO;
import error.ErrorCode;
import error.MyException;
import models.News;
import source.DataSource;

import java.time.LocalDateTime;
import java.util.List;

public class NewsRepository implements GeneralRepository<News, NewsDTO> {
    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public News create(NewsDTO t) {
        News news = new News();

        news.setAuthorId(t.getAuthorId());
        news.setTitle(t.getTitle());
        news.setContent(t.getContent());
        news.setCreateDate(LocalDateTime.now()); //ISO
        news.setLastUpdateDate(LocalDateTime.now()); //ISO

        int index = dataSource.getNewsList().size() - 1;
        news.setId(dataSource.getNewsList().get(index).getId());

        dataSource.getNewsList().add(news);
        return news;
    }

    @Override
    public List<News> getAll() {
        return dataSource.getNewsList();
    }

    @Override
    public NewsDTO getById(Long id) {
        News news = dataSource.getNewsList().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(new MyException(ErrorCode.NO_SUCH_NEW.getErrorData())));

            NewsDTO newsDTO = new NewsDTO();
            newsDTO.setTitle(news.getTitle());
            newsDTO.setContent(news.getContent());
            newsDTO.setAuthorId(news.getAuthorId());
            return newsDTO;
    }

    @Override
    public News update(NewsDTO t, Long id) {
        News news = dataSource.getNewsList().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(new MyException(ErrorCode.NO_SUCH_NEW.getErrorData())));

        news.setTitle(t.getTitle());
        news.setContent(t.getContent());
        news.setAuthorId(t.getAuthorId());
        news.setLastUpdateDate(LocalDateTime.now());

        return news;
    }

    @Override
    public boolean delete(Long id) {
        return dataSource.getNewsList().removeIf(news -> news.getId().equals(id));
    }
}
