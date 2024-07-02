package fpl.but.datn.dto.request;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
public class GioHangChiTietDto {

    private UUID id;
    private GioHang gioHang;
    private ChiTietSanPham chiTietSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
    private List<String> hinhAnhUrls;

}
