package fpl.but.datn.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED ERROR", HttpStatus.INTERNAL_SERVER_ERROR),

    INVALID_KEY(1001, "INVALID MESSAGE", HttpStatus.BAD_REQUEST),
    ACCOUNT_EXISTED(1002, "Tai khoan da ton tai", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Ten dang nhap phai tu 6 den 10 ky tu", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Mat khau phai tu 8 den 16 ky tu", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_EXISTED(1005, "Khong tim thay tai khoan", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1009, "Khong co quyen truy cap", HttpStatus.FORBIDDEN),
    NO_ACCOUNTS_FOUND(1007, "Không tìm thấy danh sách tài khoản nào",HttpStatus.NOT_FOUND),
    ROLES_NOT_EXISTED(1008, "Không tìm thấy roles nào", HttpStatus.NOT_FOUND)

    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = httpStatusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
