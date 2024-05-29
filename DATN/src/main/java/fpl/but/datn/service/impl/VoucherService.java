package fpl.but.datn.service.impl;

import fpl.but.datn.entity.Voucher;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.VoucherRepository;
import fpl.but.datn.service.IService;
import fpl.but.datn.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VoucherService implements IService<Voucher>, IVoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Voucher getByID(UUID id) {
        return voucherRepository.findById(id).get();
    }

    @Override
    public Voucher createAccount(Voucher request) {
        Optional<Voucher> optionalVoucher = voucherRepository.findByTen(request.getTen());

        if (optionalVoucher.isPresent()) {
            Voucher existingVoucher = optionalVoucher.get();

            if (request.equals(existingVoucher)) {
                existingVoucher.setSoLuong(existingVoucher.getSoLuong() + request.getSoLuong());
                existingVoucher.setNgaySua(new Date());
                return voucherRepository.saveAndFlush(existingVoucher);
            }
        }

        Random random = new Random();
        Voucher newVoucher = Voucher.builder()
                .id(UUID.randomUUID())
                .ma("Voucher" + random.nextInt(1000))
                .ten(request.getTen())
                .loaiGiamGia(request.getLoaiGiamGia())
                .ngayBatDau(request.getNgayBatDau())
                .ngayKetThuc(request.getNgayKetThuc())
                .giaTriToiThieu(request.getGiaTriToiThieu())
                .giaTriGiam(request.getGiaTriGiam())
                .soLuong(request.getSoLuong())
                .ngayTao(new Date())
                .ngaySua(new Date())
                .trangThai(Boolean.TRUE)
                .build();

        return voucherRepository.save(newVoucher);
    }


    @Override
    public Voucher update(UUID uuid, Voucher voucher) {
        Optional<Voucher> optionalVoucher = voucherRepository.findById(uuid);
        if (optionalVoucher.isPresent()) {
            Voucher existingVoucher = optionalVoucher.get();
            existingVoucher.setMa(voucher.getMa());
            existingVoucher.setTen(voucher.getTen());
            existingVoucher.setLoaiGiamGia(voucher.getLoaiGiamGia());
            existingVoucher.setNgayBatDau(voucher.getNgayBatDau());
            existingVoucher.setNgayKetThuc(voucher.getNgayKetThuc());
            existingVoucher.setGiaTriGiam(voucher.getGiaTriGiam());
            existingVoucher.setGiaTriToiThieu(voucher.getGiaTriToiThieu());
            existingVoucher.setSoLuong(voucher.getSoLuong());
            existingVoucher.setNgaySua(new Date());
            existingVoucher.setTrangThai(voucher.getTrangThai());
            return voucherRepository.saveAndFlush(existingVoucher);
        } else {
            throw new AppException(ErrorCode.VOUCHER_NOT_EXISTED);
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<Voucher> optionalVoucher = voucherRepository.findById(id);
        if (optionalVoucher.isPresent()) {

            Voucher existingVoucher = optionalVoucher.get();
            existingVoucher.setTrangThai(Boolean.FALSE);
            existingVoucher.setNgaySua(new Date());
            voucherRepository.save(existingVoucher);
        } else {
            throw new AppException(ErrorCode.VOUCHER_NOT_EXISTED);
        }
    }

    @Override
    public List<Voucher> getAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Page<Voucher> getAllPageable(Pageable pageable) {
        return voucherRepository.findAll(pageable);
    }

    @Override
    public Voucher findByMaGiam(String ma) {
        return voucherRepository.findByMa(ma).orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_EXISTED));
    }

    @Override
    public Voucher findByTen(String ten) {
        return voucherRepository.findByTen(ten).orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_EXISTED));
    }
}
