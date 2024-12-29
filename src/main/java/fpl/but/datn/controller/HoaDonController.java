package fpl.but.datn.controller;

import fpl.but.datn.dto.request.*;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IHoaDonService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@RestController
@RequestMapping("/api/hoa-don")
public class HoaDonController {

    @Autowired
    private IHoaDonService hoaDonService;

    @GetMapping("/find-time")
    public ApiResponse<List<HoaDon>> getHoaDon(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<HoaDon> hoaDonList = hoaDonService.getHoaDonBetweenDates(startDate, endDate);
        ApiResponse<List<HoaDon>> apiResponse = new ApiResponse<>();

        if (!hoaDonList.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách hóa đơn thành công");
            apiResponse.setResult(hoaDonList);
        } else {
            throw new AppException(ErrorCode.ORDER_NOT_EXISTED);
        }

        return apiResponse;
    }


    @GetMapping("/findByKhachHang/{id}")
    ApiResponse<List<HoaDonDto>> findByIdKhachHang(@PathVariable UUID id) {
        ApiResponse<List<HoaDonDto>> apiResponse = new ApiResponse<>();
        if (id != null) {
            List<HoaDonDto> dto = TranferDatas.convertListHoaDonToDto(hoaDonService.findHoaDonByKhachHang(id));
            apiResponse.setMessage("Lấy Hóa đơn thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }

    @GetMapping("/doanhthu")
    public ApiResponse<BigDecimal> tinhDoanhThu() {
        BigDecimal doanhThu = hoaDonService.tinhTongDoanhThu();
        ApiResponse<BigDecimal> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Tính tổng doanh thu thành công");
        apiResponse.setResult(doanhThu);

        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<HoaDon> createHoaDon(@RequestBody @Valid HoaDonDto request) {
        ApiResponse<HoaDon> apiResponse = new ApiResponse<>();
        if (request != null) {
            apiResponse.setResult(hoaDonService.create(TranferDatas.convertToEntity(request)));
            apiResponse.setMessage("Tạo hóa đơn thành công");
        } else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return apiResponse;
    }

    @PutMapping("/update/{id}")
    ApiResponse<HoaDon> update(@RequestBody HoaDonDto request, @PathVariable UUID id) {
        ApiResponse<HoaDon> apiResponse = new ApiResponse<>();

        if (request != null) {
            apiResponse.setResult(hoaDonService.update(TranferDatas.convertToEntity(request), id));
            apiResponse.setMessage("Cập nhật thành công");
        } else {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
        return apiResponse;
    }

    @PutMapping("/yeuCauSuaHoaDon/{id}")
    ApiResponse<HoaDon> yeuCauSuaHoaDon(@RequestBody HoaDonDto request, @PathVariable UUID id) {
        ApiResponse<HoaDon> apiResponse = new ApiResponse<>();

        if (request != null) {
            apiResponse.setResult(hoaDonService.yeuCauSuaHoaDon(TranferDatas.convertToEntity(request), id));
            apiResponse.setMessage("Yêu cầu sửa thành công");
        } else {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
        return apiResponse;
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<Boolean> deleteCungHoaDon(@PathVariable String id) {
        UUID idHoaDon = null;
        if (id != null) {
            idHoaDon = UUID.fromString(id);
            hoaDonService.xoaCungHoaDon(idHoaDon);
        }
        return ApiResponse.<Boolean>builder().build();
    }

    @GetMapping("/all")
    ApiResponse<List<HoaDonDto>> getAll() {
        List<HoaDonDto> dto = TranferDatas.convertListHoaDonToDto(hoaDonService.getAll());
        ApiResponse<List<HoaDonDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách hoa don thành công");
            apiResponse.setResult(dto);
        } else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/soluong")
    public ApiResponse<Integer> tinhTongSoLuongSanPham() {
        int tongSoLuong = hoaDonService.tinhTongSoLuongSanPham();
        ApiResponse<Integer> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Tính tổng số lượng sản phẩm thành công");
        apiResponse.setResult(tongSoLuong);

        return apiResponse;
    }

    @GetMapping("/byTrangThaiAndKhachHang")
    public ApiResponse<List<HoaDonDto>> getHoaDonsByTrangThaiAndKhachHang(
            @RequestParam Integer trangThai, @RequestParam UUID khachHangId) {
        List<HoaDon> hoaDons = hoaDonService.getHoaDonsByTrangThaiAndKhachHang(trangThai, khachHangId);
        List<HoaDonDto> dto = TranferDatas.convertListHoaDonToDto(hoaDons);
        ApiResponse<List<HoaDonDto>> apiResponse = new ApiResponse<>();

        if (!dto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách hóa đơn thành công");
            apiResponse.setResult(dto);
        } else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/find/{ma}")
    ApiResponse<HoaDonDto> findByMa(@PathVariable String ma) {
        ApiResponse<HoaDonDto> apiResponse = new ApiResponse<>();
        HoaDonDto dto = TranferDatas.convertToDto(hoaDonService.findByMa(ma).get());
        apiResponse.setMessage("Lấy hoa don thành công");
        apiResponse.setResult(dto);
        return apiResponse;
    }

    @GetMapping("/findHd/{ma}")
    ApiResponse<HoaDonDto> findByMaKH(@PathVariable String ma) {
        ApiResponse<HoaDonDto> apiResponse = new ApiResponse<>();
        HoaDonDto dto = TranferDatas.convertToDto(hoaDonService.findByMaKH(ma).get());
        if (dto != null) {
            apiResponse.setMessage("Lấy hoa don thành công");
            apiResponse.setResult(dto);
        } else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/findHDMaAndKhachHang/{ma}")
    ApiResponse<HoaDonDto> findByMaAndKhachHang(@PathVariable String ma, @RequestParam UUID khachHangId) {
        ApiResponse<HoaDonDto> apiResponse = new ApiResponse<>();
        HoaDonDto dto = TranferDatas.convertToDto(hoaDonService.findByMaAndKhachHang(ma, khachHangId).get());
        if (dto != null) {
            apiResponse.setMessage("Lấy hoa don thành công");
            apiResponse.setResult(dto);
        } else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<HoaDonDto> detail(@PathVariable String id) {
        ApiResponse<HoaDonDto> apiResponse = new ApiResponse<>();
        UUID idHoaDon = null;
        if (id != null) {
            idHoaDon = UUID.fromString(id);
            HoaDonDto dto = TranferDatas.convertToDto(hoaDonService.findById(idHoaDon));
            apiResponse.setMessage("Lấy Hóa đơn thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> open(@PathVariable String id) {
        UUID idHoaDon = null;
        if (id != null) {
            idHoaDon = UUID.fromString(id);
            hoaDonService.delete(idHoaDon);
        }
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/allPage")
    ApiResponse<Page<HoaDonDto>> getDanhMuc(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDon> hoaDonPage = hoaDonService.getAllHoaDonPageable(pageable);
        List<HoaDonDto> listDto = TranferDatas.convertListHoaDonToDto(hoaDonPage.getContent());

        ApiResponse<Page<HoaDonDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách hóa đơn thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, hoaDonPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_ACCOUNTS_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/getHoaDonsByTranThai/{trangThai}")
    ApiResponse<Page<HoaDonDto>> getDanhMuc(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size,
                                            @PathVariable Integer trangThai) {

        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDon> hoaDonPage = hoaDonService.getHoaDonsByTrangThai(pageable, trangThai);
        List<HoaDonDto> listDto = TranferDatas.convertListHoaDonToDto(hoaDonPage.getContent());
        ApiResponse<Page<HoaDonDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách Hóa đơn thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, hoaDonPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }

        return apiResponse;
    }

    @PutMapping("/updateTrangThai/{id}")
    public ApiResponse<HoaDon> updateTrangThai(
            @PathVariable UUID id,
            @RequestParam Integer trangThai,
            @RequestBody HoaDonDto hoaDonDto) {
        HoaDon exsitHoaDon = hoaDonService.findById(id);
        ApiResponse<HoaDon> apiResponse = new ApiResponse<>();
        boolean canUpdate;

        if (exsitHoaDon == null) {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
        if (exsitHoaDon.getTrangThai() == 2 && trangThai == 5){
            hoaDonService.huyDonDaXuLy(TranferDatas.convertToEntity(hoaDonDto),trangThai);
            apiResponse.setResult(hoaDonService.findById(id));
            apiResponse.setMessage("Cập nhật thành công");
            return apiResponse;
        }else {
            canUpdate  = hoaDonService.canUpdateTrangThai(exsitHoaDon.getTrangThai(), trangThai, hoaDonDto.getGhiChu());
        }

        if (!canUpdate) {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }

        apiResponse.setResult(hoaDonService.updateTrangThai(id, trangThai, hoaDonDto.getGhiChu()));
        apiResponse.setMessage("Cập nhật thành công");
        return apiResponse;
    }

    @GetMapping("/thongke/doanhthu/ngay")
    public ApiResponse<Map<LocalDate, BigDecimal>> thongKeDoanhThuTheoNgay() {
        Map<LocalDate, BigDecimal> doanhThuTheoNgay = hoaDonService.thongKeDoanhThuTheoNgay();
        ApiResponse<Map<LocalDate, BigDecimal>> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Thống kê doanh thu theo ngày thành công");
        apiResponse.setResult(doanhThuTheoNgay);

        return apiResponse;
    }

    @GetMapping("/thongke/soluong/ngay")
    public ApiResponse<Map<LocalDate, Integer>> thongKeSoLuongTheoNgay() {
        Map<LocalDate, Integer> soLuongTheoNgay = hoaDonService.thongKeSoLuongTheoNgay();
        ApiResponse<Map<LocalDate, Integer>> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Thống kê số lượng sản phẩm theo ngày thành công");
        apiResponse.setResult(soLuongTheoNgay);

        return apiResponse;
    }

    @GetMapping("/thongke/doanhthu/tuan")
    public ApiResponse<Map<Integer, BigDecimal>> thongKeDoanhThuTheoTuan() {
        Map<Integer, BigDecimal> doanhThuTheoTuan = hoaDonService.thongKeDoanhThuTheoTuan();
        ApiResponse<Map<Integer, BigDecimal>> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Thống kê doanh thu theo tuần thành công");
        apiResponse.setResult(doanhThuTheoTuan);

        return apiResponse;
    }

    @GetMapping("/thongke/soluong/tuan")
    public ApiResponse<Map<Integer, Integer>> thongKeSoLuongTheoTuan() {
        Map<Integer, Integer> soLuongTheoTuan = hoaDonService.thongKeSoLuongTheoTuan();
        ApiResponse<Map<Integer, Integer>> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Thống kê số lượng sản phẩm theo tuần thành công");
        apiResponse.setResult(soLuongTheoTuan);

        return apiResponse;
    }

    @GetMapping("/thongke/doanhthu/thang")
    public ApiResponse<Map<Integer, BigDecimal>> thongKeDoanhThuTheoThang() {
        Map<Integer, BigDecimal> doanhThuTheoThang = hoaDonService.thongKeDoanhThuTheoThang();
        ApiResponse<Map<Integer, BigDecimal>> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Thống kê doanh thu theo tháng thành công");
        apiResponse.setResult(doanhThuTheoThang);

        return apiResponse;
    }

    @GetMapping("/thongke/soluong/thang")
    public ApiResponse<Map<Integer, Integer>> thongKeSoLuongTheoThang() {
        Map<Integer, Integer> soLuongTheoThang = hoaDonService.thongKeSoLuongTheoThang();
        ApiResponse<Map<Integer, Integer>> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Thống kê số lượng sản phẩm theo tháng thành công");
        apiResponse.setResult(soLuongTheoThang);

        return apiResponse;
    }

    @GetMapping("/tangtruong/doanhthu")
    public ApiResponse<Map<String, BigDecimal>> tinhTangTruongDoanhThu() {
        int namNay = Year.now().getValue();

        BigDecimal tangTruong = hoaDonService.tinhPhanTramTangTruongDoanhThu(namNay);

        Map<String, BigDecimal> result = new HashMap<>();
        result.put("tangTruongPhanTram", tangTruong);

        ApiResponse<Map<String, BigDecimal>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Tính phần trăm tăng trưởng doanh thu thành công");
        apiResponse.setResult(result);

        return apiResponse;
    }
    @PutMapping("/suaHoaDon")
    public ApiResponse<HoaDon> updateHoaDon(@RequestBody HoaDonSua request) {
        ApiResponse<HoaDon> apiResponse = new ApiResponse<>();

        try {
            HoaDon updatedHoaDon = hoaDonService.updateHoaDon(request.getChiTietList(), request.getHoaDon(), request.getNguoiDung());
            apiResponse.setMessage("Cập nhật hoá đơn thành công");
            apiResponse.setResult(updatedHoaDon);
        } catch (AppException e) {
            apiResponse.setMessage("Có lỗi xảy ra khi cập nhật hoá đơn:" + e.getMessage());
        } catch (Exception e) {
            apiResponse.setMessage("Có lỗi không mong muốn xảy ra khi cập nhật hoá đơn:" + e.getMessage());
        }

        return apiResponse;
    }



}