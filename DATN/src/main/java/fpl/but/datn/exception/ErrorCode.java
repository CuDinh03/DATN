package fpl.but.datn.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED ERROR"),

    INVALID_KEY(1001, "INVALID MESSAGE"),
    ACCOUNT_EXISTED(1002, "Tai khoan da ton tai"),
    USERNAME_INVALID(1003, "Ten dang nhap phai tu 6 den 10 ky tu"),
    PASSWORD_INVALID(1004, "Mat khau phai tu 8 den 16 ky tu"),
    ACCOUNT_NOT_EXISTED(1005, "Khong tim thay tai khoan")
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
