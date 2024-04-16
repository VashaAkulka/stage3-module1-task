package com.mjc.school.repository.source;

import com.mjc.school.repository.error.ErrorCode;
import com.mjc.school.repository.error.MyException;
import com.mjc.school.repository.models.AuthorModel;
import lombok.Getter;
import com.mjc.school.repository.models.NewsModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DataSource {
    private final String pathNews = "module-repository/src/main/resources/news.bin";
    private final String pathAuthor = "module-repository/src/main/resources/author.bin";

    private final List<NewsModel> newsList = new ArrayList<>();
    private final List<AuthorModel> authorList = new ArrayList<>();

    static private DataSource instance = null;

    private DataSource() {

    }

    static public DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
            instance.readNews();
            instance.readAuthors();
        }
        return instance;
    }

    private void readNews() {

        try(FileInputStream fis = new FileInputStream(pathNews)) {
            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);

                while(true) {
                    try {
                        newsList.add((NewsModel) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(new MyException(ErrorCode.NO_SUCH_FILE.getErrorData()));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void readAuthors() {

        try (FileInputStream fis = new FileInputStream(pathAuthor)) {
            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);

                while(true) {
                    try {
                        authorList.add((AuthorModel) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(new MyException(ErrorCode.NO_SUCH_FILE.getErrorData()));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeNews() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathNews))) {
            for (NewsModel news : newsList) oos.writeObject(news);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(new MyException(ErrorCode.NO_SUCH_FILE.getErrorData()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeAuthors() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathAuthor))) {
            for (AuthorModel author : authorList) oos.writeObject(author);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(new MyException(ErrorCode.NO_SUCH_FILE.getErrorData()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
