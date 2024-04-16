package validate;

import error.MyException;

public interface GeneralValidation<T> {
    public void validate(T t) throws MyException;
}
