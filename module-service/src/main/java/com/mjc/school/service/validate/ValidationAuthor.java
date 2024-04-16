package com.mjc.school.service.validate;

import com.mjc.school.service.dto.AuthorDTO;
import com.mjc.school.repository.error.ErrorCode;
import com.mjc.school.repository.error.MyException;

public class ValidationAuthor implements GeneralValidation<AuthorDTO> {
    @Override
    public void validate(AuthorDTO authorDTO) throws MyException {
        if (authorDTO.getName().isEmpty()) throw new MyException(ErrorCode.EMPTY_FIELD.getErrorData());
        if (authorDTO.getName().length() < 3 || authorDTO.getName().length() > 15) throw new MyException(ErrorCode.FIELD_NAME_INVALID_LENGTH.getErrorData());
    }
}
