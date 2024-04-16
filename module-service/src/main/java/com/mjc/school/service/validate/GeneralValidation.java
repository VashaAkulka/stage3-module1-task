package com.mjc.school.service.validate;

import com.mjc.school.repository.error.MyException;

public interface GeneralValidation<T> {
    public void validate(T t) throws MyException;
}
