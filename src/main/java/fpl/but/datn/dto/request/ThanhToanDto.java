package fpl.but.datn.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ThanhToanDto {
    private HoaDonDto hoaDonDto;
    private List<GioHangChiTietDto> gioHangChiTietDtoList;
}
