package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.GioHangChiTietDto;
import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.entity.*;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.GioHangHoaDonRepository;
import fpl.but.datn.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ThanhToanService {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private CTSanPhamService ctSanPhamService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private HoaDonGioHangService hoaDonGioHangService;
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private GioHangHoaDonRepository gioHangHoaDonRepository;
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    public void thanhToanSanPham(HoaDon request, List<GioHangChiTiet> listGioHangCt) {
        System.out.println(request.getId());

        if (request != null) {
            HoaDon hoaDon = hoaDonService.findById(request.getId());
            System.out.println("");
            System.out.println(hoaDon);
            if (hoaDon != null) {
                hoaDon.setTrangThai(Boolean.FALSE);
                hoaDon.setTongTien(request.getTongTien());
                hoaDon.setNgaySua(new Date());
                hoaDon.setNgayTao(new Date());
                if (request.getKhachHang() != null) {
                    hoaDon.setKhachHang(request.getKhachHang());
                }
                hoaDonService.update(hoaDon, hoaDon.getId());

                for (GioHangChiTiet ghCt : listGioHangCt) {
                    HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                            .id(UUID.randomUUID())
                            .giaBan(ghCt.getChiTietSanPham().getGiaBan())
                            .soLuong(ghCt.getSoLuong())
                            .ngaySua(new Date())
                            .ngayTao(new Date())
                            .chiTietSanPham(ghCt.getChiTietSanPham())
                            .hoaDon(hoaDon)
                            .trangThai(Boolean.FALSE)
                            .build();
                    System.out.println("");
                    System.out.println(hoaDonChiTiet);
                    this.hoaDonChiTietService.create(hoaDonChiTiet);
                }

                List<HoaDonChiTiet> hoaDonChiTiets = this.hoaDonChiTietService.getHoaDonChiTietByIdHoaDon(hoaDon.getId());

                for (HoaDonChiTiet hdct : hoaDonChiTiets) {
                    hdct.setTrangThai(Boolean.TRUE);
                    this.hoaDonChiTietService.update(hdct, hdct.getId());
                }
                GioHangHoaDon gioHangHoaDon = this.hoaDonGioHangService.findByIdHoaDon(hoaDon.getId());
                GioHang gioHang = this.gioHangService.findById(gioHangHoaDon.getGioHang().getId());
                gioHang.setTrangThai(Boolean.FALSE);
                this.gioHangService.update(gioHang, gioHang.getId());
            }
        }
    }

//    public void thanhToanHoaDon(HoaDonDto hoaDonDto, List<GioHangChiTietDto> gioHangChiTietDtoList) throws Exception {
//        // Lấy hóa đơn
//        HoaDon hoaDon = hoaDonRepository.findById(hoaDonDto.getId())
//                .orElseThrow(() -> new Exception("Hóa đơn không tồn tại"));
//
//        // Lấy giỏ hàng liên quan đến hóa đơn
//        GioHang gioHang = gioHangRepository.findByHoaDonId(hoaDonDto.getId());
//        if (gioHang == null) {
//            throw new Exception("Giỏ hàng không tồn tại");
//        }
//
//        // Cập nhật tổng tiền và trạng thái hóa đơn
//        hoaDon.setTongTien(hoaDonDto.getTongTien());
//        hoaDon.setTrangThai(false);
//        hoaDon.setNgaySua(new Date());
//        hoaDonRepository.save(hoaDon);
//
//        // Cập nhật trạng thái giỏ hàng
//        gioHang.setTrangThai(false);
//        gioHangRepository.save(gioHang);
//
//        // Tạo hóa đơn chi tiết từ DTO
//        for (GioHangChiTietDto gioHangChiTietDto : gioHangChiTietDtoList) {
//            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
//            hoaDonChiTiet.setHoaDon(hoaDon);
//            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(gioHangChiTietDto.getChiTietSanPhamId())
//                    .orElseThrow(() -> new Exception("Chi tiết sản phẩm không tồn tại"));
//            hoaDonChiTiet.setChiTietSanPham(chiTietSanPham);
//            hoaDonChiTiet.setSoLuong(gioHangChiTietDto.getSoLuong());
//            hoaDonChiTiet.setGiaBan(chiTietSanPham.getGiaBan());
//            hoaDonChiTiet.setNgayTao(new Date());
//            hoaDonChiTiet.setNgaySua(new Date());
//            hoaDonChiTiet.setTrangThai(true);
//            hoaDonChiTietRepository.save(hoaDonChiTiet);
//        }
//    }
}
