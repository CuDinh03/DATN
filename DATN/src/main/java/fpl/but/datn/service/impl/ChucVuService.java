package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.ChucVuRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChucVuService {


    ChucVuRepository chucVuRepository;

    public ChucVu getChucVu(UUID id) {
        return chucVuRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROLES_NOT_EXISTED));
    }
}
