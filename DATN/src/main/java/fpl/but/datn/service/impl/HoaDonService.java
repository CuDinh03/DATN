package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.*;
import fpl.but.datn.service.ICTSanPhamService;
import fpl.but.datn.service.IHoaDonService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HoaDonService implements IHoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    private GioHangHoaDonRepository hoaDonGioHangRepository;
    @Autowired
    private CTSanPhamRepository ctSanPhamRepository;

    @Autowired
    private ICTSanPhamService ictSanPhamService;

    @Autowired
    private VoucherRepository voucherRepository;


    @Override
    public List getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon create(HoaDon request) {
        HoaDon hoaDon = new HoaDon();
        Random random = new Random();
        hoaDon.setId(UUID.randomUUID());
        hoaDon.setMa("HD" + random.nextInt(1000));
        hoaDon.setVoucher(request.getVoucher());
        hoaDon.setNgaySua(new Date());
        hoaDon.setNgayTao(new Date());
        hoaDon.setTongTien(request.getTongTien());
        hoaDon.setNguoiDung(request.getNguoiDung());
        hoaDon.setKhachHang(request.getKhachHang());
        hoaDon.setTongTienGiam(request.getTongTienGiam());
        hoaDon.setGhiChu(request.getGhiChu());
        hoaDon.setVoucher(request.getVoucher());
        hoaDon.setTrangThai(1);
        return hoaDonRepository.save(hoaDon);
    }

    public HoaDon createHoaDonOnl(HoaDon request) {
        return hoaDonRepository.save(request);
    }

    @Override
    public HoaDon update(HoaDon request, UUID id) {
        HoaDon hoaDon = findById(id);
        hoaDon.setVoucher(request.getVoucher());
        hoaDon.setId(id);
        hoaDon.setNgaySua(new Date());
        hoaDon.setNgayTao(new Date());
        hoaDon.setTongTien(request.getTongTien());
        hoaDon.setTongTienGiam(request.getTongTienGiam());
        hoaDon.setGhiChu(request.getGhiChu());
        hoaDon.setVoucher(request.getVoucher());
        hoaDon.setTrangThai(request.getTrangThai());
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon yeuCauSuaHoaDon(HoaDon request, UUID id) {
        HoaDon hoaDon = findById(id);
        hoaDon.setNgaySua(new Date());
        hoaDon.setId(id);
        hoaDon.setGhiChu(request.getGhiChu());
        hoaDon.setTrangThai(6); // trang thai yeu câu sửa hóa đơn
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public boolean canUpdateTrangThai(int currentTrangThai, int newTrangThai, String ghiChu) {
        if (newTrangThai == 5 && ghiChu == null) {
            return false;
        }
        switch (currentTrangThai) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return newTrangThai == 5 || (newTrangThai == currentTrangThai + 1 && ghiChu != null);
            case 6:
                return newTrangThai == 2 || (newTrangThai == 5 && ghiChu != null);
            case 5:
            default:
                return false;
        }
    }


    public void huyDonDaXuLy(HoaDon hoaDon, int trangThai){
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.findAllHoaDonChiTietByIdHoaDon(hoaDon.getId());
        for (HoaDonChiTiet chiTiet: list){
            Optional<ChiTietSanPham> optional = ctSanPhamRepository.findById(chiTiet.getChiTietSanPham().getId());
            if (optional.isPresent()){
                ChiTietSanPham chiTietSanPham = optional.get();
                int soLuongCu = chiTietSanPham.getSoLuong();
                chiTietSanPham.setSoLuong(soLuongCu + chiTiet.getSoLuong());
                chiTietSanPham.setNgaySua(new Date());

                ctSanPhamRepository.save(chiTietSanPham);
                chiTiet.setNgaySua(new Date());
                chiTiet.setTrangThai(5);
                hoaDonChiTietRepository.save(chiTiet);


            }
        }

        hoaDon.setTrangThai(5);
        hoaDon.setNgaySua(new Date());

        if (hoaDon.getVoucher() != null){
            Voucher voucher = hoaDon.getVoucher();
            int soLuong = voucher.getSoLuong();
            voucher.setSoLuong(soLuong+1);
            voucher.setNgaySua(new Date());
            voucherRepository.save(voucher);
        }

        hoaDonRepository.save(hoaDon);
    }


    @Override
    public void delete(UUID id) {
        HoaDon hoaDon = findById(id);
        hoaDon.setTrangThai(5);
        hoaDon.setNgaySua(new Date());
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public boolean xoaCungHoaDon(UUID id) {
        Optional<HoaDon> optional = hoaDonRepository.findById(id);

        if (optional.isPresent()) {
            List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findAllHoaDonChiTietByIdHoaDon(id);
            hoaDonChiTietRepository.deleteAll(hoaDonChiTiets);
            GioHangHoaDon gioHangHoaDon = hoaDonGioHangRepository.findByIdHoaDon(id);
            if (gioHangHoaDon != null) {
                hoaDonGioHangRepository.delete(gioHangHoaDon);
            }
            HoaDon hoaDon = optional.get();
            hoaDonRepository.delete(hoaDon);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public HoaDon updateTrangThai(UUID id, Integer trangThai, String ghiChu) {
        HoaDon hoaDon = findById(id);
        if (hoaDon.getTrangThai() == 1) {
            List<HoaDonChiTiet> list = hoaDonChiTietRepository.findAllHoaDonChiTietByIdHoaDon(hoaDon.getId());
            List<ChiTietSanPham> listCt = ctSanPhamRepository.getCtspByHoaDon(hoaDon.getId());
            for (ChiTietSanPham ctsp : listCt) {
                for (HoaDonChiTiet hoaDonChiTiet : list) {
                    if (ctsp.getId().equals(hoaDonChiTiet.getChiTietSanPham().getId())) {
                        Integer soLuong = ctsp.getSoLuong();
                        if (soLuong >= hoaDonChiTiet.getSoLuong()) {
                            ctsp.setSoLuong(soLuong - hoaDonChiTiet.getSoLuong());
                            ctsp.setNgaySua(new Date());
                            ctSanPhamRepository.save(ctsp);
                        }
                    }
                }
            }
        }
        hoaDon.setTrangThai(trangThai);
        hoaDon.setGhiChu(ghiChu);
        hoaDon.setNgaySua(new Date());
        return hoaDonRepository.save(hoaDon);
    }

    public void open(UUID id) {
    }

    @Override
    public HoaDon findById(UUID id) {
        return hoaDonRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
    }

    @Override
    public Page<HoaDon> getAllHoaDonPageable(Pageable pageable) {
        return hoaDonRepository.findAllPage(pageable);
    }

    @Override
    public Optional<HoaDon> findByMa(String ma) {
        return hoaDonRepository.findByMa(ma);
    }

    @Override
    public List<HoaDon> getHoaDonBetweenDates(Date startDate, Date endDate) {
        return hoaDonRepository.findByNgayTaoBetween(startDate, endDate);
    }

    @Override
    public Optional<HoaDon> findByMaAndKhachHang(String ma, UUID khachHangId) {
        return hoaDonRepository.findByMaAndKhachHang(ma, khachHangId);
    }

    @Override
    public Page<HoaDon> getHoaDonsByTrangThai(Pageable pageable, Integer trangThai) {
        return hoaDonRepository.findByTrangThai(pageable, trangThai);
    }

    public List<HoaDon> getHoaDonsByTrangThaiAndKhachHang(Integer trangThai, UUID khachHangId) {
        return hoaDonRepository.findByTrangThaiAndKhachHangId(trangThai, khachHangId);
    }

    @Override
    public Optional<HoaDon> findByMaKH(String ma) {
        return hoaDonRepository.findByMaKH(ma);
    }

    @Override
    public List<HoaDon> findHoaDonByKhachHang(UUID idKhachHang) {
        return hoaDonRepository.findHoaDonByKhachHang(idKhachHang);
    }

    @Override
    public BigDecimal tinhTongDoanhThu() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThai1(4);
        return hoaDonList.stream()
                .map(HoaDon::getTongTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int tinhTongSoLuongSanPham() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThai1(4);
        return hoaDonList.stream()
                .flatMap(hoaDon -> hoaDon.getHoaDonChiTietList().stream())
                .mapToInt(HoaDonChiTiet::getSoLuong)
                .sum();
    }

    public Map<LocalDate, BigDecimal> thongKeDoanhThuTheoNgay() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThai1(4);
        return hoaDonList.stream()
                .collect(Collectors.groupingBy(
                        hoaDon -> hoaDon.getNgayTao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        Collectors.reducing(BigDecimal.ZERO, HoaDon::getTongTien, BigDecimal::add)
                ));
    }

    public Map<LocalDate, Integer> thongKeSoLuongTheoNgay() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThai1(4);
        return hoaDonList.stream()
                .flatMap(hoaDon -> hoaDon.getHoaDonChiTietList().stream())
                .collect(Collectors.groupingBy(
                        hoaDonChiTiet -> hoaDonChiTiet.getNgayTao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        Collectors.summingInt(HoaDonChiTiet::getSoLuong)
                ));
    }

    public Map<Integer, BigDecimal> thongKeDoanhThuTheoTuan() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThai1(4);
        return hoaDonList.stream()
                .collect(Collectors.groupingBy(
                        hoaDon -> hoaDon.getNgayTao().toInstant().atZone(ZoneId.systemDefault()).get(ChronoField.ALIGNED_WEEK_OF_YEAR),
                        Collectors.reducing(BigDecimal.ZERO, HoaDon::getTongTien, BigDecimal::add)
                ));
    }

    public Map<Integer, Integer> thongKeSoLuongTheoTuan() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThai1(4);
        return hoaDonList.stream()
                .flatMap(hoaDon -> hoaDon.getHoaDonChiTietList().stream())
                .collect(Collectors.groupingBy(
                        hoaDonChiTiet -> hoaDonChiTiet.getNgayTao().toInstant().atZone(ZoneId.systemDefault()).get(ChronoField.ALIGNED_WEEK_OF_YEAR),
                        Collectors.summingInt(HoaDonChiTiet::getSoLuong)
                ));
    }

    public Map<Integer, BigDecimal> thongKeDoanhThuTheoThang() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThai1(4);
        return hoaDonList.stream()
                .collect(Collectors.groupingBy(
                        hoaDon -> hoaDon.getNgayTao().toInstant().atZone(ZoneId.systemDefault()).getMonthValue(),
                        Collectors.reducing(BigDecimal.ZERO, HoaDon::getTongTien, BigDecimal::add)
                ));
    }

    public Map<Integer, Integer> thongKeSoLuongTheoThang() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThai1(4);
        return hoaDonList.stream()
                .flatMap(hoaDon -> hoaDon.getHoaDonChiTietList().stream())
                .collect(Collectors.groupingBy(
                        hoaDonChiTiet -> hoaDonChiTiet.getNgayTao().toInstant().atZone(ZoneId.systemDefault()).getMonthValue(),
                        Collectors.summingInt(HoaDonChiTiet::getSoLuong)
                ));
    }

    public BigDecimal tinhTongDoanhThuTheoNam(int nam) {
        LocalDate fromDate = LocalDate.of(nam, 1, 1);
        LocalDate toDate = LocalDate.of(nam, 12, 31);

        Date startDate = Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusSeconds(1).toInstant());

        List<HoaDon> hoaDonList = hoaDonRepository.findByNgayTaoBetween(startDate, endDate);
        return hoaDonList.stream()
                .map(HoaDon::getTongTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal tinhPhanTramTangTruongDoanhThu(int namNay) {
        int namTruoc = namNay - 1;

        BigDecimal doanhThuNamNay = tinhTongDoanhThuTheoNam(namNay);
        BigDecimal doanhThuNamTruoc = tinhTongDoanhThuTheoNam(namTruoc);

        BigDecimal tangTruong = BigDecimal.ZERO;
        if (!doanhThuNamTruoc.equals(BigDecimal.ZERO)) {
            tangTruong = doanhThuNamNay.subtract(doanhThuNamTruoc)
                    .divide(doanhThuNamTruoc, 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }
        return tangTruong;
    }

    public HoaDon updateHoaDon(List<HoaDonChiTietDto> chiTietList, HoaDon hoaDon, NguoiDung nguoiDung) {
        // Tìm hóa đơn cũ
        Optional<HoaDon> optionalHoaDonCu = hoaDonRepository.findById(hoaDon.getId());
        if (!optionalHoaDonCu.isPresent()) {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }

        HoaDon hoaDonCu = optionalHoaDonCu.get();
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findAllHoaDonChiTietByIdHoaDon(hoaDon.getId());

        for (HoaDonChiTiet hdDta : hoaDonChiTiets) {
            for (HoaDonChiTietDto hdNew : chiTietList) {
                if (hdNew.getId().equals(hdDta.getId())) {
                    // Xử lý chi tiết sản phẩm cũ
                    MauSac mauCu = hdDta.getChiTietSanPham().getMauSac();
                    MauSac mauMoi = hdNew.getChiTietSanPham().getMauSac();

                    KichThuoc kichThuocCu = hdDta.getChiTietSanPham().getKichThuoc();
                    KichThuoc kichThuocMoi = hdNew.getChiTietSanPham().getKichThuoc();

                    if (mauMoi.getId().equals(mauCu.getId()) && kichThuocCu.getId().equals(kichThuocMoi.getId())) {
                        Optional<ChiTietSanPham> optionalChiTietSanPham = ctSanPhamRepository.findById(hdDta.getChiTietSanPham().getId());
                        if (!optionalChiTietSanPham.isPresent()) {
                            throw new AppException(ErrorCode.NO_PRODUCT_DETAIL_FOUND);
                        }

                        ChiTietSanPham chiTietSanPham = optionalChiTietSanPham.get();
                        int soLuongCu = chiTietSanPham.getSoLuong();
                        int soLuongThayDoi;
                        // Cập nhật số lượng sản phẩm nếu không thay đổi chi tiết
                        if (hdNew.getSoLuong() > hdDta.getSoLuong()) {
                            soLuongThayDoi = hdNew.getSoLuong() - hdDta.getSoLuong();
                            chiTietSanPham.setSoLuong(soLuongCu - soLuongThayDoi);
                        } else if (hdNew.getSoLuong() < hdDta.getSoLuong()) {
                            soLuongThayDoi = hdDta.getSoLuong() - hdNew.getSoLuong();
                            chiTietSanPham.setSoLuong(soLuongCu + soLuongThayDoi);
                        } else {
                            continue; // Số lượng không thay đổi, không cần cập nhật
                        }

                        chiTietSanPham.setNgaySua(new Date());
                        ctSanPhamRepository.save(chiTietSanPham);
                        hdNew.setNgaySua(new Date());
                        hoaDonChiTietRepository.save(TranferDatas.convertToEntity(hdNew));
                        continue;
                    }

                    if (mauMoi.getId() != mauCu.getId() || kichThuocCu.getId() != kichThuocMoi.getId()) {
                        ChiTietSanPham chiTietSanPhamMoi = ictSanPhamService.getByMKS(hdNew.getChiTietSanPham().getSanPham().getId(), kichThuocMoi.getId(), mauMoi.getId());
                        ChiTietSanPham chiTietSanPhamCu = ictSanPhamService.getByMKS(hdNew.getChiTietSanPham().getSanPham().getId(), kichThuocCu.getId(), mauCu.getId());
                        chiTietSanPhamMoi.setSoLuong(chiTietSanPhamMoi.getSoLuong() - hdNew.getSoLuong());
                        chiTietSanPhamMoi.setNgaySua(new Date());
                        ctSanPhamRepository.save(chiTietSanPhamMoi);
                        chiTietSanPhamCu.setSoLuong(chiTietSanPhamCu.getSoLuong() + hdDta.getSoLuong());
                        chiTietSanPhamCu.setNgaySua(new Date());
                        ctSanPhamRepository.save(chiTietSanPhamCu);
                        hdNew.setChiTietSanPham(chiTietSanPhamMoi);
                        hdNew.setNgaySua(new Date());
                        hoaDonChiTietRepository.save(TranferDatas.convertToEntity(hdNew));
                    }
                }
            }
        }

        // Cập nhật thông tin hóa đơn
        hoaDonCu.setGhiChu(hoaDon.getGhiChu());
        hoaDonCu.setNgaySua(new Date());
        hoaDonCu.setNguoiDung(nguoiDung);
        hoaDonCu.setTongTien(hoaDon.getTongTien());
        hoaDonCu.setTongTienGiam(hoaDon.getTongTienGiam());

        return hoaDonRepository.save(hoaDonCu);
    }


}