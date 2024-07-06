package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ThanhToanDto;
import fpl.but.datn.dto.request.ThanhToanOnl;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.service.impl.*;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thanhtoan")
public class ThanhToanController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private HoaDonGioHangService hoaDonGioHangService;

    @Autowired
    private ThanhToanService thanhToanService;

    @PostMapping()
    public ApiResponse<ThanhToanDto> thanhtoan(@RequestBody ThanhToanDto thanhToanDto) {
        ApiResponse<ThanhToanDto> apiResponse = new ApiResponse<>();
        try {
            if (thanhToanDto.getHoaDonDto() != null && thanhToanDto.getGioHangChiTietDtoList() != null) {
                thanhToanService.thanhToanSanPham(TranferDatas.convertToEntity(thanhToanDto.getHoaDonDto()), TranferDatas.convertListGioHangChiTietToEntity(thanhToanDto.getGioHangChiTietDtoList()));
                apiResponse.setMessage("Thanh toán thành công");
                apiResponse.setResult(thanhToanDto);
            } else {
                apiResponse.setMessage("Thông tin thanh toán không hợp lệ");
                apiResponse.setResult(null);
            }
        } catch (Exception e) {
            apiResponse.setMessage("Đã xảy ra lỗi khi thanh toán: " + e.getMessage());
            apiResponse.setResult(null);
        }

        return apiResponse;
    }

    @PostMapping("/onl")
    public ApiResponse<ThanhToanOnl> thanhtoanOnl(@RequestBody ThanhToanOnl thanhToanOnl) {
        ApiResponse<ThanhToanOnl> apiResponse = new ApiResponse<>();

        try {
            if (thanhToanOnl.getGioHang() != null && thanhToanOnl.getGioHangChiTietList() != null) {
                thanhToanService.thanhToanSanPhamOnline(
                        thanhToanOnl.getGioHang(),
                        thanhToanOnl.getTongTien(),
                        thanhToanOnl.getTongTienGiam(),
                        thanhToanOnl.getVoucher(),
                        thanhToanOnl.getDiaChiGiaoHang(),
                        thanhToanOnl.getNote(),
                        thanhToanOnl.getGioHangChiTietList()
                );

                apiResponse.setMessage("Thanh toán thành công");
                apiResponse.setResult(thanhToanOnl);
            } else {
                apiResponse.setMessage("Thông tin thanh toán không hợp lệ");
                apiResponse.setResult(null);
            }
        } catch (Exception e) {
            apiResponse.setMessage("Đã xảy ra lỗi khi thanh toán: " + e.getMessage());
            apiResponse.setResult(null);
        }

        return apiResponse;
    }


}
