package error;

public enum ErrorCode {
    NO_SUCH_NEW("101", "NO SUCH NEW"),
    NO_SUCH_AUTHOR("102", "NO SUCH AUTHOR"),
    NO_SUCH_FILE("103", "NO SUCH FILE"),
    EMPTY_FIELD("201", "EMPTY FIELD"),
    FIELD_TITLE_INVALID_LENGTH("202", "INVALID LENGTH TITLE"),
    FIELD_CONTENT_INVALID_LENGTH("203", "INVALID LENGTH CONTENT"),
    FIELD_NAME_INVALID_LENGTH("204", "INVALID LENGTH NAME");


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
