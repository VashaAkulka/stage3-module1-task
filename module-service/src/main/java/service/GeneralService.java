package service;

import error.MyException;

import java.util.List;

public interface GeneralService <T> {
    T create(T dto) throws MyException;
    List<T> getAll();
    T getById(Long id) throws MyException;
    T update(T dto, Long id) throws MyException;
    boolean delete(Long id);
}
