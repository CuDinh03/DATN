package fpl.but.datn.service.impl;

import fpl.but.datn.dto.TaiKhoanDto;
import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    public TaiKhoan createAccount(TaiKhoan request){
        TaiKhoan taiKhoan = new TaiKhoan();

        taiKhoan.setMa(request.getMa());
        taiKhoan.setId(UUID.randomUUID());
//        taiKhoan.setIdChucVu( new ChucVu(UUID.randomUUID(),"Quản lý", new Date(),new Date(), Boolean.TRUE));
//        taiKhoan.setIdChucVu( request.getIdChucVu());
        taiKhoan.setTenDangNhap(request.getTenDangNhap());
        taiKhoan.setMatKhau(request.getMatKhau());
        taiKhoan.setNgayTao(new Date());
        taiKhoan.setNgaySua(new Date());
        taiKhoan.setTrangThai(request.getTrangThai());

       return taiKhoanRepository.save(taiKhoan);
    }

    public List<TaiKhoan> getAllTaiKhoan(){
        return taiKhoanRepository.findAll();
    }
    public TaiKhoan getTaiKhoan(UUID id){
        return taiKhoanRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Tai khoan khong tim thay") );
    }
    public TaiKhoan updateTaiKhoan(UUID id, TaiKhoan request){
            TaiKhoan taiKhoan = getTaiKhoan(id);


//        taiKhoan.setIdChucVu( new ChucVu(UUID.randomUUID(),"Quản lý", new Date(),new Date(), Boolean.TRUE));
//        taiKhoan.setIdChucVu( request.getIdChucVu());
        taiKhoan.setMatKhau(request.getMatKhau());
        taiKhoan.setNgaySua(new Date());
        taiKhoan.setTrangThai(request.getTrangThai());

       return taiKhoanRepository.save(taiKhoan);
    }

    public void deleteTaiKhoan(UUID id){
        taiKhoanRepository.deleteById(id);
    }

}
