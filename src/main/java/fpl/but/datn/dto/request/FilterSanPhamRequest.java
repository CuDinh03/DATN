package fpl.but.datn.dto.request;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.MauSac;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterSanPhamRequest {
    private MauSac mauSac;
    private KichThuoc kichThuoc;
    private DanhMuc danhMuc;
}
