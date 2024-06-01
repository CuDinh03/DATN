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
    UNAUTHORIZED(1007, "Khong co quyen truy cap", HttpStatus.FORBIDDEN),
    NO_ACCOUNTS_FOUND(1008, "Không tìm thấy danh sách tài khoản nào",HttpStatus.NOT_FOUND),
    ROLES_NOT_EXISTED(1009, "Không tìm thấy roles nào", HttpStatus.NOT_FOUND),
    UPDATE_FAILED(1010, "Cập nhật thất bại", HttpStatus.BAD_REQUEST),
    DELETE_FAILED(1011, "Xoá thất bại", HttpStatus.BAD_REQUEST),
    NO_REPORT_FOUND(1012, "Không tìm thấy danh sách báo cáo nào",HttpStatus.NOT_FOUND ),
    NO_CHATLIEU_FOUND(1013, "Không tìm thấy danh sách chất liệu nào",HttpStatus.NOT_FOUND),
    REPORT_EXISTED(1014, "báo cáo đã ton tai",HttpStatus.BAD_REQUEST),
    CHATLIEU_EXISTED(1015, "Chất liệu đã ton tai",HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1016, "Không tìm thấy chất liệu",HttpStatus.NOT_FOUND),
    CATEGORY_EXISTED(1017, "Danh mục này đã tồn tai", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_NULL(1018, "Tên danh mục không được để trống", HttpStatus.BAD_REQUEST),
    CATEGORY_CODE_NULL(1019, "Mã danh mục không được để trống", HttpStatus.BAD_REQUEST),
    NO_ORDER_FOUND(1020, "Không tìm thấy danh sách hoa don nào",HttpStatus.NOT_FOUND ),
    NO_LISTSPChiTiet_FOUND(1020, "Không tìm thấy danh sách SPCT nào",HttpStatus.NOT_FOUND ),

    MA_MAUSAC_TRUNG(1021, "Trùng mã màu sắc", HttpStatus.BAD_REQUEST),
    LIST_COLOR_NOT_FOUND(1022, "Không tìm thấy danh sách màu", HttpStatus.NOT_FOUND),
    COLOR_NOT_FOUND(1023, "Màu sắc đang tìm không có", HttpStatus.NOT_FOUND),

    MA_KICHTHUOC_TRUNG(1024, "Trùng mã kich thước", HttpStatus.BAD_REQUEST),
    LIST_KICHTHUOC_NOT_FOUND(1025, "Không tìm thấy danh sách kích thước", HttpStatus.NOT_FOUND),
    KICHTHUOC_NOT_FOUND(1026, "Kích thước đang tìm không có", HttpStatus.NOT_FOUND),

    MA_NHANVIEN_TRUNG(1024, "Trùng mã nhân viên", HttpStatus.BAD_REQUEST),
    LIST_NHANVIEN_NOT_FOUND(1025, "Không tìm thấy danh sách nhân viên", HttpStatus.NOT_FOUND),
    NHANVIEN_NOT_FOUND(1026, "Nhân viên đang tìm không có", HttpStatus.NOT_FOUND),

    MA_KHACHHANG_TRUNG(1024, "Trùng mã khach hang", HttpStatus.BAD_REQUEST),
    LIST_KHACHHANG_NOT_FOUND(1025, "Không tìm thấy danh sách khach hang", HttpStatus.NOT_FOUND),
    KHACHHANG_NOT_FOUND(1026, "khach hang đang tìm không có", HttpStatus.NOT_FOUND),

    MA_PTTHANHTOAN_TRUNG(1024, "Trùng mã phương thức thanh toán", HttpStatus.BAD_REQUEST),
    LIST_PTTHANHTOAN_NOT_FOUND(1025, "Không tìm thấy danh sách phương thức thanh toán", HttpStatus.NOT_FOUND),
    PTTHANHTOAN_NOT_FOUND(1026, "Phương thức thanh toán đang tìm không có", HttpStatus.NOT_FOUND),
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