package validate;

import dto.AuthorDTO;
import error.ErrorCode;
import error.MyException;

public class ValidationAuthor implements GeneralValidation<AuthorDTO> {
    @Override
    public void validate(AuthorDTO authorDTO) throws MyException {
        if (authorDTO.getName().isEmpty()) throw new MyException(ErrorCode.EMPTY_FIELD.getErrorData());
    }
}
