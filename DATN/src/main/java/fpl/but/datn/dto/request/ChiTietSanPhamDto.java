package fpl.but.datn.dto.request;

import fpl.but.datn.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class ChiTietSanPhamDto {
    private UUID id;
    private String ma;
    private SanPham idSanPham;
    private HinhAnh idHinhAnh;
    private ThuongHieu idThuongHieu;
    private ChatLieu idChatLieu;
    private DanhMuc idDanhMuc;
    private KichThuoc idKichThuoc;
    private MauSac idMauSac;
    private Integer soLuong;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private Date ngayNhap;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
