package fpl.but.datn.controller;

import fpl.but.datn.dto.request.TaiKhoanDto;
import fpl.but.datn.dto.request.VoucherDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.entity.Voucher;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.VoucherService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/voucher")
@Slf4j
public class VoucherController {
    private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);

    @Autowired
    private VoucherService voucherService;

    @PostMapping("/create")
    ApiResponse<Voucher> createVoucher(@RequestBody @Valid VoucherDto request) {
        ApiResponse<Voucher> apiResponse = new ApiResponse<>();
        if (request != null) {
            apiResponse.setResult(voucherService.create(TranferDatas.convertToEntity(request)));
        }
        return apiResponse;
    }

    @GetMapping("/allVouchers")
    ApiResponse<List<VoucherDto>> getAllVoucher() {

        ApiResponse<List<VoucherDto>> apiResponse = new ApiResponse<>();

        try {
            logger.debug("Fetching all vouchers");
            List<VoucherDto> listDto = TranferDatas.convertListVoucherToDto(voucherService.getAll());
            System.out.println(listDto);
            if (!listDto.isEmpty()) {
             apiResponse.setMessage("Lấy danh sách voucher thành công");
             apiResponse.setResult(listDto);

            } else {
                throw new AppException(ErrorCode.NO_VOUCHER_FOUND);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Invalid UUID encountered in getAllVoucher: " + e.getMessage(), e);
            throw e;
        }

        return apiResponse;
    }


    @GetMapping("/all")
    ApiResponse<Page<VoucherDto>> getVouchers(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Voucher> vouchers = voucherService.getAllPageable(pageable);
        List<VoucherDto> listDto = TranferDatas.convertListVoucherToDto(vouchers.getContent());

        ApiResponse<Page<VoucherDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách voucher thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, vouchers.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_VOUCHER_FOUND);
        }

        return apiResponse;
    }

    @PutMapping("/{id}")
    ApiResponse<VoucherDto> updateVoucher(@PathVariable String id,
                                          @RequestBody VoucherDto request) {
        UUID idVoucher = null;
        if (id != null) idVoucher = UUID.fromString(id);
        Voucher voucher = new Voucher();
        if (request != null)
            voucher = voucherService.update(idVoucher, TranferDatas.convertToEntity(request));

        return ApiResponse.<VoucherDto>builder()
                .message("Sửa thành công voucher với id = " + id )
                .result(TranferDatas.convertToDto(voucher))
                .build();
    }
    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteAccount(@PathVariable String id) {
        UUID idVoucher = null;
        if (id != null) idVoucher = UUID.fromString(id);
        voucherService.delete(idVoucher);
        return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/open/{id}")
    ApiResponse<Void> open(@PathVariable String id) {
        UUID idVoucher = null;
        if (id != null) {
            idVoucher = UUID.fromString(id);
            voucherService.open(idVoucher);
        } return ApiResponse.<Void>builder().build();
    }


    @GetMapping("/{id}")
    ApiResponse<VoucherDto> selecVoucher(@PathVariable String id){
        UUID idVoucher = null;
        if (id != null) idVoucher = UUID.fromString(id);
        Voucher voucher = voucherService.getByID(idVoucher);
        if (voucher == null) {
            throw new AppException(ErrorCode.VOUCHER_NOT_EXISTED);

        }
        return ApiResponse.<VoucherDto>builder()
                .message("ss")
                .result(TranferDatas.convertToDto(voucher))
                .build();
    }


}
