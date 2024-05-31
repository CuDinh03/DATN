package fpl.but.datn.dto.request;

import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.HoaDon;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
public class GioHangHoaDonDto {

    private UUID id;
    private GioHang idGioHang;
    private HoaDon idHoaDon;
}
