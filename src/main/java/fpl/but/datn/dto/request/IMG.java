package fpl.but.datn.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class IMG {

    private List<HinhAnhDto> anhDtoListt;
    private List<ChiTietSanPhamDto> chiTietSanPhamDto;
}
