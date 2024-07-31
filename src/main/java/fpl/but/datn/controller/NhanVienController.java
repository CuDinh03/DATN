package fpl.but.datn.controller;

import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.NguoiDung;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.service.impl.NguoiDungService;
import fpl.but.datn.service.impl.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/nguoi-dung")
public class NhanVienController {

    @Autowired

    private NguoiDungService nguoiDungService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/{tenDangNhap}")
    public ApiResponse<NguoiDung> findByUsername(@PathVariable("tenDangNhap") String tenDangNhap) {
        ApiResponse<NguoiDung> apiResponse = new ApiResponse<>();

        Optional<TaiKhoan> optionalTaiKhoan = taiKhoanService.findByTenDangNhap(tenDangNhap);
        if (optionalTaiKhoan.isEmpty()) {
            apiResponse.setMessage("Không tìm thấy tài khoản với tên đăng nhập: " + tenDangNhap);
            apiResponse.setCode(404);
            return apiResponse;
        }

        Optional<NguoiDung> optionalNguoiDung = nguoiDungService.findByTaiKhoanid(optionalTaiKhoan.get().getId());
        if (optionalNguoiDung.isEmpty()) {
            apiResponse.setMessage("Không tìm thấy người dùng tương ứng với tài khoản: " + tenDangNhap);
            apiResponse.setCode(404);
            return apiResponse;
        }

        NguoiDung nguoiDung = optionalNguoiDung.get();
        apiResponse.setMessage("Lấy thông tin người dùng thành công");
        apiResponse.setResult(nguoiDung);
        apiResponse.setCode(1000);
        return apiResponse;
    }
}
