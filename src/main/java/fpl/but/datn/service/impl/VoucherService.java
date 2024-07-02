package fpl.but.datn.service.impl;

import fpl.but.datn.entity.SanPham;
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

import java.math.BigDecimal;
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
        public Voucher create(Voucher request) {
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
                    .trangThai(1)
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
            existingVoucher.setTrangThai(0);
            existingVoucher.setNgaySua(new Date());
            voucherRepository.save(existingVoucher);
        } else {
            throw new AppException(ErrorCode.VOUCHER_NOT_EXISTED);
        }
    }

    public void open(UUID id) {
        Voucher voucher = getByID(id);
        voucher.setTrangThai(1);
        voucher.setNgaySua(new Date());
        voucherRepository.save(voucher);

    }

    @Override
    public List<Voucher> getAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Page<Voucher> getAllPageable(Pageable pageable) {
        return voucherRepository.findAllPage(pageable);
    }

    @Override
    public Voucher findByMaGiam(String ma) {
        return voucherRepository.findByMa(ma).orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_EXISTED));
    }

    @Override
    public Voucher findByTen(String ten) {
        return voucherRepository.findByTen(ten).orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_EXISTED));
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Random random = new Random();
            String ma = "Voucher" + random.nextInt(1000); // Tạo mã ngẫu nhiên
            String ten = "Voucher " + i;
            String loaiGiamGia = "Loai " + i;
            java.util.Date currentDate = new java.util.Date();
            java.sql.Date ngayBatDau = new java.sql.Date(currentDate.getTime()); // Ngày hiện tại
            java.sql.Date ngayKetThuc = new java.sql.Date(currentDate.getTime()); // Ngày hiện tại
            BigDecimal giaTriGiam = BigDecimal.valueOf(random.nextInt(50)); // Giá trị giảm ngẫu nhiên từ 0 đến 50
            BigDecimal giaTriToiThieu = BigDecimal.valueOf(random.nextInt(500)); // Giá trị tối thiểu giảm ngẫu nhiên từ 0 đến 500
            Integer soLuong = random.nextInt(20) + 1; // Số lượng ngẫu nhiên từ 1 đến 20
            java.sql.Date ngayTao = new java.sql.Date(currentDate.getTime()); // Ngày hiện tại
            java.sql.Date ngaySua = new java.sql.Date(currentDate.getTime()); // Ngày hiện tại
            Integer trangThai = 1;

            System.out.println("INSERT INTO voucher (id, ma, ten, loai_giam_gia, ngay_bat_dau, ngay_ket_thuc, gia_tri_giam, gia_tri_toi_thieu, so_luong, ngay_tao, ngay_sua, trang_thai) VALUES ("
                    + "'" + UUID.randomUUID() + "',"
                    + "'" + ma + "',"
                    + "'" + ten + "',"
                    + "'" + loaiGiamGia + "',"
                    + "'" + ngayBatDau + "',"
                    + "'" + ngayKetThuc + "',"
                    + giaTriGiam + ","
                    + giaTriToiThieu + ","
                    + soLuong + ","
                    + "'" + ngayTao + "',"
                    + "'" + ngaySua + "',"
                    + trangThai
                    + ");");
        }
    }
}
