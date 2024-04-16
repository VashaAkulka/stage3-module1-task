package error;

public enum ErrorCode {
    NO_SUCH_NEW("101", "No such new"),
    NO_SUCH_AUTHOR("102", "No such author"),
    NO_SUCH_FILE("103", "No such file"),
    EMPTY_FIELD("201", "empty field"),
    FIELD_TITLE_INVALID_LENGTH("202", "invalid length title"),
    FIELD_CONTENT_INVALID_LENGTH("203", "invalid length content");


    //100 - repository
    //200 - service
    //300 - web

    private final String errorCode;
    private final String errorMessage;

    ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorData() {
        return "Code: " +
                errorCode +
                ", message: " +
                errorMessage;
    }
}
