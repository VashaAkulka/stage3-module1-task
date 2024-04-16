package service;

import error.MyException;

import java.util.List;

public interface GeneralService <T> {
    T create(T dto);
    List<T> getAll();
    T getById(Long id);
    T update(T dto, Long id);
    boolean delete(Long id);
}
