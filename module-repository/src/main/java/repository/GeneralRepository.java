package repository;

import error.MyException;

import java.util.List;

public interface GeneralRepository<T> {
    void save(T t);
    List<T> getAll();
    T getById(Long id) throws MyException;
    T update(T t, Long id) throws MyException;
    boolean delete(Long id);
}
