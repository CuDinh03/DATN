package fpl.but.datn.exception;

public enum ErrorCode {
    ACCOUNT_EXISTED(1001, " Tai khoan da ton tai")
    ;
     ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
