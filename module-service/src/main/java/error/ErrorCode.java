package error;

public enum ErrorCode {
    NO_SUCH_NEW("001", "No such new");

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
