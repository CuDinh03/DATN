package fpl.but.datn.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED ERROR"),

    INVALID_KEY(1001, "INVALID MESSAGE"),
    ACCOUNT_EXISTED(1002, "Tai khoan da ton tai"),
    USERNAME_INVALID(1003, "Ten dang nhap phai tu 6 den 10 ky tu"),
    PASSWORD_INVALID(1004, "Mat khau phai tu 8 den 16 ky tu"),
    ACCOUNT_NOT_EXISTED(1005, "Khong tim thay tai khoan"),
    UNAUTHENTICATED(1006, "UNAUTHENTICATED"),
    NO_ACCOUNTS_FOUND(1007, "Không tìm thấy danh sách tài khoản nào"),
    ROLES_NOT_EXISTED(1008, "Không tìm thấy roles nào"),
    NOT_ADD_CART(1009,"Khong them duoc vao gio hang"),
    NO_REPORT_FOUND(1010, "Không tìm thấy danh sách báo cáo nào"),
    NO_CHATLIEU_FOUND(1011, "Không tìm thấy danh sách chất liệu nào"),
    REPORT_EXISTED(1012, "báo cáo đã ton tai"),
    CHATLIEU_EXISTED(1013, "Chất liệu đã ton tai"),
    CATEGORY_NOT_EXISTED(1008, "Không tìm thấy chất liệu"),



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