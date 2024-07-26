package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.HoaDonChiTietRepository;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IHoaDonChiTietService;
import fpl.but.datn.service.IHoaDonService;
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
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private NguoiDungService nguoiDungService;

    @Override
    public List getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet create(HoaDonChiTiet request) {
        return hoaDonChiTietRepository.save(request);
    }

    @Override
    public HoaDonChiTiet update(HoaDonChiTiet request, UUID id) {
        HoaDonChiTiet hoaDonChiTiet = findById(id);
        hoaDonChiTiet.setHoaDon(request.getHoaDon());
        hoaDonChiTiet.setSoLuong(request.getSoLuong());
        hoaDonChiTiet.setId(id);
        hoaDonChiTiet.setNgayTao(request.getNgayTao());
        hoaDonChiTiet.setNgaySua(new Date());
        hoaDonChiTiet.setGiaBan(request.getGiaBan());
        hoaDonChiTiet.setChiTietSanPham(request.getChiTietSanPham());
        request.setTrangThai(request.getTrangThai());
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    public List<HoaDonChiTiet> updateHoaDonChiTiet(List<HoaDonChiTiet> chiTietList) {
        List<HoaDonChiTiet> updatedChiTietList = new ArrayList<>();

        for (HoaDonChiTiet chiTiet : chiTietList) {
            Optional<HoaDonChiTiet> optionalChiTiet = hoaDonChiTietRepository.findById(chiTiet.getId());

            if (optionalChiTiet.isPresent()) {
                HoaDonChiTiet chiTietCu = optionalChiTiet.get();

                // Cập nhật các thuộc tính
                chiTietCu.setChiTietSanPham(chiTiet.getChiTietSanPham());
                chiTietCu.setSoLuong(chiTiet.getSoLuong());
                chiTietCu.setNgaySua(new Date());

                updatedChiTietList.add(hoaDonChiTietRepository.save(chiTietCu));
            } else {
                throw new AppException(ErrorCode.NO_ORDER_DETAIL_FOUND);
            }
        }

        return updatedChiTietList;
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
