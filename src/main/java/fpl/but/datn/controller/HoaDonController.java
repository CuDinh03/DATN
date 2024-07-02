package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ChucVuDto;
import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.request.GioHangDto;
import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IHoaDonService;
import fpl.but.datn.service.impl.HoaDonService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hoa-don")
public class HoaDonController {

    @Autowired
    private IHoaDonService hoaDonService;

    @GetMapping("/find-time")
    public ApiResponse<List<HoaDon>> getHoaDon(
            @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
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
        if (id != null){
            List<HoaDonDto> dto = TranferDatas.convertListHoaDonToDto(hoaDonService.findHoaDonByKhachHang(id));
            apiResponse.setMessage("Lấy Hóa đơn thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<HoaDon> createHoaDon(@RequestBody @Valid HoaDonDto request){
        ApiResponse<HoaDon>  apiResponse = new ApiResponse<>();
        if (request != null){
            apiResponse.setResult(hoaDonService.create(TranferDatas.convertToEntity(request)));
            apiResponse.setMessage("Tạo hóa đơn thành công");
        }else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return apiResponse;
    }

    @PutMapping("/update/{id}")
    ApiResponse<HoaDon> update(@RequestBody HoaDonDto request, @PathVariable UUID id) {
        ApiResponse<HoaDon>  apiResponse = new ApiResponse<>();

        if (request != null){
            apiResponse.setResult(hoaDonService.update(TranferDatas.convertToEntity(request), id));
            apiResponse.setMessage("Cập nhật thành công");
        }
        else{
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
        return apiResponse;
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<Boolean> deleteCungHoaDon(@PathVariable String id){
        UUID idHoaDon = null;
        if (id != null) {
            idHoaDon = UUID.fromString(id);
            hoaDonService.xoaCungHoaDon(idHoaDon);
        }
        return ApiResponse.<Boolean>builder().build();
    }

    @GetMapping("/all")
    ApiResponse<List<HoaDonDto>> getAll(){
        List<HoaDonDto> dto = TranferDatas.convertListHoaDonToDto(hoaDonService.getAll());
        ApiResponse<List<HoaDonDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách hoa don thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
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
    ApiResponse<HoaDonDto> findByMa(@PathVariable String ma){
        ApiResponse<HoaDonDto> apiResponse =  new ApiResponse<>();
        HoaDonDto dto = TranferDatas.convertToDto(hoaDonService.findByMa(ma).get());
        apiResponse.setMessage("Lấy hoa don thành công");
        apiResponse.setResult(dto);
        return apiResponse;
    }

    @GetMapping("/findHd/{ma}")
    ApiResponse<HoaDonDto> findByMaKH(@PathVariable String ma){
        ApiResponse<HoaDonDto> apiResponse =  new ApiResponse<>();
        HoaDonDto dto = TranferDatas.convertToDto(hoaDonService.findByMaKH(ma).get());
        if (dto != null){
            apiResponse.setMessage("Lấy hoa don thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/findHDMaAndKhachHang/{ma}")
    ApiResponse<HoaDonDto> findByMaAndKhachHang(@PathVariable String ma, @RequestParam UUID khachHangId){
        ApiResponse<HoaDonDto> apiResponse =  new ApiResponse<>();
        HoaDonDto dto = TranferDatas.convertToDto(hoaDonService.findByMaAndKhachHang(ma,khachHangId).get());
        if (dto != null){
            apiResponse.setMessage("Lấy hoa don thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<HoaDonDto> detail(@PathVariable String id) {
        ApiResponse<HoaDonDto> apiResponse = new ApiResponse<>();
        UUID idHoaDon = null;
        if (id != null){
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
        } return ApiResponse.<Void>builder().build();
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
    public ApiResponse<HoaDon> updateTrangThai(@PathVariable UUID id, @RequestParam Integer trangThai ) {
        ApiResponse<HoaDon> apiResponse = new ApiResponse<>();
        if (trangThai != null) {
            apiResponse.setResult(hoaDonService.updateTrangThai(id, trangThai));
            apiResponse.setMessage("Cập nhật thành công");
        } else {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
        return apiResponse;
    }



}