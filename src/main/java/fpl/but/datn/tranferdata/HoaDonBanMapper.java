package fpl.but.datn.tranferdata;

import fpl.but.datn.dto.request.HoaDonBanDto;
import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.entity.HoaDon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HoaDonBanMapper {
    HoaDonBanMapper INSTANCE = Mappers.getMapper(HoaDonBanMapper.class);

    HoaDonBanDto toDto(HoaDon hoaDon);
    HoaDon toEntity(HoaDonBanDto hoaDonBanDto);

    List<HoaDonBanDto> toListDto(List<HoaDon> hoaDons);

    List<HoaDon> toListEntity(List<HoaDonBanDto> hoaDonBanDtoList);
}
