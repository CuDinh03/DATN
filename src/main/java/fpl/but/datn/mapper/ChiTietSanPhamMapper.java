package fpl.but.datn.mapper;

import fpl.but.datn.dto.request.ChiTietSanPhamDto;
import fpl.but.datn.entity.ChiTietSanPham;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ChiTietSanPhamMapper {
    ChiTietSanPhamMapper INTANCE = Mappers.getMapper(ChiTietSanPhamMapper.class);

    ChiTietSanPhamDto toDto(ChiTietSanPham chiTietSanPham);
}
