package fpl.but.datn.entity;

public enum PhuongThucGiaoHang {
    Standard("Giao hàng tiêu chuẩn",1, "Giao trong vòng 3-4 ngày làm việc"),
    Express("Giao hàng hoả tốc", 2,"Giao nhanh trong 2 tiếng nếu trong nội thành")
    ;

    PhuongThucGiaoHang(String ten, Integer code, String moTa) {
        this.ten = ten;
        this.code = code;
        this.moTa = moTa;
    }

    private String ten;
    private Integer code;
    private String moTa;
}