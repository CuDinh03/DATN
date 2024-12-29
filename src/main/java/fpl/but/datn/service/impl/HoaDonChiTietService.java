package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.HoaDonChiTietRepository;
import fpl.but.datn.service.IHoaDonChiTietService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HoaDonChiTietService implements IHoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public List getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet create(HoaDonChiTiet request) {
        return hoaDonChiTietRepository.save(request);
    }

    @Override
    public HoaDonChiTiet update(HoaDonChiTiet hoaDonChiTiet, UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        Optional<HoaDonChiTiet> optional = hoaDonChiTietRepository.findById(id);
        if (optional.isPresent()){
            HoaDonChiTiet hoaDonChiTiet = optional.get();
            hoaDonChiTietRepository.delete(hoaDonChiTiet);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void open(UUID id) {

    }

    @Override
    public HoaDonChiTiet findById(UUID id) {
        return hoaDonChiTietRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
    }

    @Override
    public Page<HoaDonChiTiet> getAllDanhMucPageable(Pageable pageable) {
        return null;
    }

    public List<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(UUID idHoaDon) {
        return hoaDonChiTietRepository.findAllHoaDonChiTietByIdHoaDon(idHoaDon);
    }

    @Override
    public List<Object[]> findAllChiTietAndHinhAnhByIdHoaDon(UUID idGioHang) {

        return hoaDonChiTietRepository.findAllChiTietAndHinhAnhByIdHoaDon(idGioHang);
    }


    public List<HoaDonChiTietDto> thongKeSanPhamBanNhieuNhat() {
        List<HoaDonChiTiet> danhSachHoaDonChiTiet = hoaDonChiTietRepository.findTopSellingProducts();

        return danhSachHoaDonChiTiet.stream()
                .map(TranferDatas::convertToDto)
                .collect(Collectors.toList());
    }




}
