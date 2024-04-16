package com.mjc.school.repository;

import com.mjc.school.repository.error.MyException;

import java.util.List;

public interface GeneralRepository<T> {
    T create(T t);
    List<T> readAll();
    T readById(Long id) throws MyException;
    T update(T t, Long id) throws MyException;
    Boolean delete(Long id);
}
