package source;

import lombok.Getter;
import models.Author;
import models.News;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DataSource {
    private final String pathNews = "resources/news.txt";
    private final String pathAuthor = "resources/author.txt";

    List<News> newsList = new ArrayList<>();
    List<Author> authorList = new ArrayList<>();

    static private DataSource instance = null;

    private DataSource() {

    }

    static public DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
            instance.readAuthors();
            instance.readNews();
        }
        return instance;
    }

    private void readNews() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathNews))) {
            while (ois.available() > 0) {
                newsList.add((News) ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void readAuthors() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathAuthor))) {
            while (ois.available() > 0) {
                authorList.add((Author) ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeNews() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathNews))) {
            for (News news : newsList) oos.writeObject(news);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeAuthors() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathAuthor))) {
            for (Author author : authorList) oos.writeObject(author);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAllData() {
        writeNews();
        writeAuthors();
    }
}
