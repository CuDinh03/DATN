package fpl.but.datn.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class IMG {

    private List<HinhAnhRequest> anhDtoListt;
    private List<ChiTietSanPhamDto> chiTietSanPhamDto;
}
