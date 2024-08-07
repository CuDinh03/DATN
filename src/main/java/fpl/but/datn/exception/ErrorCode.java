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
    CATEGORY_NOT_EXISTED(1016, "Không tìm thấy danh sách danh mục nào",HttpStatus.NOT_FOUND),
    VOUCHER_NOT_EXISTED(1017, "Không tìm thấy Voucher này!",HttpStatus.NOT_FOUND),
    NO_VOUCHER_FOUND(1018, "Không tìm thấy Voucher nào!",HttpStatus.NOT_FOUND),
    SDT_ALREADY_USED(1019, "Số điện thoại này đã được đăng ký!",HttpStatus.NOT_FOUND),
    NO_CUSTOMERS_FOUND(1020, "Không tìm thấy khách hàng nào!",HttpStatus.NOT_FOUND),
    HINHANH_EXISTED(1021, "Hình ảnh đã ton tai",HttpStatus.BAD_REQUEST),
    HINHANH_NOT_EXISTED(1022, "Không tìm thấy hình ảnh",HttpStatus.NOT_FOUND),
    CATEGORY_EXISTED(1023, "Danh mục này đã tồn tai", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_NULL(1024, "Tên danh mục không được để trống", HttpStatus.BAD_REQUEST),
    CATEGORY_CODE_NULL(1025, "Mã danh mục không được để trống", HttpStatus.BAD_REQUEST),
    NO_ORDER_FOUND(1026, "Không tìm thấy danh sách hoa don nào",HttpStatus.NOT_FOUND ),
    ORDER_NOT_EXISTED(1027, "Không tìm thấy hoa don",HttpStatus.NOT_FOUND),
    NO_CARTDETAIl_FOUND(1028, "Không tìm thấy danh sách gio hang chi tiet nào",HttpStatus.NOT_FOUND ),
    NO_PRODUCT_DETAIL_FOUND(1029, "Không tìm thấy danh sách chi tiet san pham nào",HttpStatus.NOT_FOUND),
    NO_ORDER_DETAIL_FOUND(1030, "Không tìm thấy danh sách chi tiet hoa don nào",HttpStatus.NOT_FOUND ),
    USER_NOT_EXISTED(1031, "Không tìm thấy người dùng ",HttpStatus.NOT_FOUND),
    NO_LISTSPChiTiet_FOUND(1032, "Không tìm thấy SPCT ",HttpStatus.NOT_FOUND),
    NO_IMAGES_FOUND(1033, "Không tìm thấy danh sách Hinh anh nào",HttpStatus.NOT_FOUND ),
    NO_LISTSP_FOUND(1034, "Không tìm thấy SP ",HttpStatus.NOT_FOUND),
    NO_MAUSAC_FOUND(1035, "Không tìm thấy danh sách màu sắc nào ",HttpStatus.NOT_FOUND),
    MAUSAC_EXISTED(1036, "Màu sắc đã ton tai",HttpStatus.BAD_REQUEST),
    NO_KICHTHUOC_FOUND(1037, "Không tìm thấy danh sách kích thước nào ",HttpStatus.NOT_FOUND),
    KICHTHUOC_EXISTED(1038, "Kích thước đã ton tai",HttpStatus.BAD_REQUEST),
    NO_THUONGHIEU_FOUND(1039, "Không tìm thấy danh sách thương hiệu nào ",HttpStatus.NOT_FOUND),
    THUONGHIEU_EXISTED(1040, "Thương hiệu đã ton tai",HttpStatus.BAD_REQUEST),
    NO_SANPHAM_FOUND(1041, "Không tìm thấy danh sách sản phẩm nào ",HttpStatus.NOT_FOUND),
    SANPHAM_EXISTED(1042, "Sản phẩm đã ton tai",HttpStatus.BAD_REQUEST),
    MA_MAUSAC_TRUNG(1043, "Trùng mã màu sắc", HttpStatus.BAD_REQUEST),
    LIST_COLOR_NOT_FOUND(1044, "Không tìm thấy danh sách màu", HttpStatus.NOT_FOUND),
    COLOR_NOT_FOUND(1045, "Màu sắc đang tìm không có", HttpStatus.NOT_FOUND),
    MA_KICHTHUOC_TRUNG(1046, "Trùng mã kich thước", HttpStatus.BAD_REQUEST),
    LIST_KICHTHUOC_NOT_FOUND(1047, "Không tìm thấy danh sách kích thước", HttpStatus.NOT_FOUND),
    KICHTHUOC_NOT_FOUND(1048, "Kích thước đang tìm không có", HttpStatus.NOT_FOUND),
    MA_NHANVIEN_TRUNG(1049, "Trùng mã nhân viên", HttpStatus.BAD_REQUEST),
    LIST_NHANVIEN_NOT_FOUND(1050, "Không tìm thấy danh sách nhân viên", HttpStatus.NOT_FOUND),
    NHANVIEN_NOT_FOUND(1051, "Nhân viên đang tìm không có", HttpStatus.NOT_FOUND),
    MA_KHACHHANG_TRUNG(1052, "Trùng mã khach hang", HttpStatus.BAD_REQUEST),
    LIST_KHACHHANG_NOT_FOUND(1053, "Không tìm thấy danh sách khach hang", HttpStatus.NOT_FOUND),
    KHACHHANG_NOT_FOUND(1054, "khach hang đang tìm không có", HttpStatus.NOT_FOUND),
    MA_PTTHANHTOAN_TRUNG(1055, "Trùng mã phương thức thanh toán", HttpStatus.BAD_REQUEST),
    LIST_PTTHANHTOAN_NOT_FOUND(1056, "Không tìm thấy danh sách phương thức thanh toán", HttpStatus.NOT_FOUND),
    PTTHANHTOAN_NOT_FOUND(1057, "Phương thức thanh toán đang tìm không có", HttpStatus.NOT_FOUND),
    NO_LIST_SANPHAM_FOUND(1058, "Không tìm thấy danh sách sản phẩm nào ",HttpStatus.NOT_FOUND),
    CTSP_EXISTED(1059, "Sản phẩm chi tiết này đã tồn tại", HttpStatus.BAD_REQUEST),
    LIST_RATING_NOT_FOUND(2001, "Không tìm thấy danh sách đánh giá nào", HttpStatus.BAD_REQUEST),
    ADD_RATING_NOT_FOUND(2002, "Thêm đánh giá thất bại", HttpStatus.BAD_REQUEST),
    LIST_ORDER_FOUND(2003, "Không tìm thấy danh sách đánh giá nào", HttpStatus.BAD_REQUEST),
    KH_EXISTED_MAIL( 1060,"Mail này đã được sử dụng", HttpStatus.BAD_REQUEST ),
    DIACHI_NOT_FOUND( 2004,"Khong tim thay danh sach dia chi", HttpStatus.BAD_REQUEST );

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