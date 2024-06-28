package fpl.but.datn.dto.request;
import fpl.but.datn.entity.*;
import lombok.Data;

import java.util.List;
@Data
public class SaveCtspRequest {
    private SanPham sanPham;
    private List<MauSac> mauSacList;
    private ChatLieu chatLieu;
    private DanhMuc danhMuc;
    private ThuongHieu thuongHieu;
    private List<KichThuoc> kichThuocList;

}

