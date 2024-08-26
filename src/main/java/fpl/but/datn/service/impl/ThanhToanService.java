package fpl.but.datn.service.impl;
import fpl.but.datn.dto.request.GioHangChiTietDto;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.GioHangChiTietRepository;
import fpl.but.datn.repository.HoaDonRepository;
import fpl.but.datn.service.IService;
import fpl.but.datn.service.IThanhToanService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

@Service
public class ThanhToanService implements IThanhToanService, IService<ThanhToan> {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private GiaoHangService giaoHangService;
    @Autowired
    private EmailSenderService emailSenderService;


    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private HoaDonGioHangService hoaDonGioHangService;

    @Autowired
    private VoucherService voucherService;

    @Override
    public ThanhToan getByID(UUID id) {
        return null;
    }

    @Override
    public ThanhToan create(ThanhToan thanhToan) {

        return null;
    }

    @Override
    public ThanhToan update(UUID uuid, ThanhToan thanhToan) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<ThanhToan> getAll() {
        return null;
    }

    @Override
    public Page<ThanhToan> getAllPageable(Pageable pageable) {
        return null;
    }

    public void thanhToanSanPham(HoaDon request,
                                 List<GioHangChiTiet> listGioHangCt) {


        if (request != null) {
            HoaDon hoaDon = hoaDonService.findById(request.getId());
            if (hoaDon != null) {
                hoaDon.setTrangThai(4);
                hoaDon.setVoucher(request.getVoucher());
                hoaDon.setTongTienGiam(request.getTongTienGiam());
                hoaDon.setGhiChu(request.getGhiChu());
                hoaDon.setTongTien(request.getTongTien());
                hoaDon.setNguoiDung(request.getNguoiDung());
                hoaDon.setNgaySua(new Date());
                hoaDon.setNgayTao(new Date());
                if (request.getKhachHang() != null) {
                    hoaDon.setKhachHang(request.getKhachHang());
                }
                hoaDonService.update(hoaDon, hoaDon.getId());
                for (GioHangChiTiet ghCt : listGioHangCt) {
                    System.out.println(ghCt);
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                    hoaDonChiTiet.setId(UUID.randomUUID());
                    hoaDonChiTiet.setGiaBan(ghCt.getChiTietSanPham().getGiaBan());
                    hoaDonChiTiet.setSoLuong(ghCt.getSoLuong());
                    hoaDonChiTiet.setNgayTao(new Date());
                    hoaDonChiTiet.setNgaySua(new Date());
                    hoaDonChiTiet.setChiTietSanPham(ghCt.getChiTietSanPham());
                    hoaDonChiTiet.setHoaDon(hoaDon);
                    hoaDonChiTiet.setTrangThai(4);
                    this.hoaDonChiTietService.create(hoaDonChiTiet);
                }

                List<HoaDonChiTiet> hoaDonChiTiets = this.hoaDonChiTietService.getHoaDonChiTietByIdHoaDon(hoaDon.getId());
                for (HoaDonChiTiet hdct : hoaDonChiTiets) {
                    hdct.setTrangThai(4);
                    this.hoaDonChiTietService.update(hdct, hdct.getId());
                }
                GioHangHoaDon gioHangHoaDon = this.hoaDonGioHangService.findByIdHoaDon(hoaDon.getId());
                GioHang gioHang = this.gioHangService.findById(gioHangHoaDon.getGioHang().getId());
                gioHang.setTrangThai(4);
                this.gioHangService.update(gioHang, gioHang.getId());

                if (hoaDon.getVoucher() != null) {
                    Voucher voucher = hoaDon.getVoucher();
                    int soLuongConLai = voucher.getSoLuong() - 1;
                    if (soLuongConLai < 0) {
                        throw new AppException(ErrorCode.NO_VOUCHER_FOUND); // tam de vay
                    }
                    voucher.setSoLuong(soLuongConLai);
                    if (soLuongConLai < 10 && soLuongConLai > 0) {
                        System.out.println("Voucher sắp hết, chỉ còn lại " + soLuongConLai + " voucher!");
                    }
                    voucherService.update(voucher.getId(),voucher);
                }
            }
        }
    }

    @Transactional
    public void thanhToanSanPhamOnline(GioHang requestGh, BigDecimal tongTien, BigDecimal tongTienGiam,
                                       Voucher voucher, String diaChiGiaoHang, String ghiChu, List<GioHangChiTietDto> listGioHangCt) {
        System.out.println("============================");
        System.out.println(listGioHangCt);
        if (requestGh == null || requestGh.getId() == null) {
            throw new IllegalArgumentException("GioHang request is invalid");
        }
        if (tongTien == null || tongTienGiam == null) {
            throw new IllegalArgumentException("TongTien or TongTienGiam is invalid");
        }
        if (listGioHangCt == null || listGioHangCt.isEmpty()) {
            throw new IllegalArgumentException("ListGioHangCt is empty or null");
        }



        GioHang gioHang = Optional.ofNullable(this.gioHangService.findById(requestGh.getId()))
                .orElseThrow(() -> new IllegalArgumentException("GioHang not found with id: " + requestGh.getId()));
        GiaoHang giaoHang = new GiaoHang();
        giaoHang.setId(UUID.randomUUID());
        giaoHang.setKhachHang(requestGh.getKhachHang());
        HoaDon hoaDon = HoaDon.builder()
                .nguoiDung(null)
                .khachHang(gioHang.getKhachHang())
                .tongTien(tongTien)
                .tongTienGiam(tongTienGiam)
                .voucher(voucher)
                .ghiChu(ghiChu)
                .build();
        System.out.println("===================");
        System.out.println("trc khi luu");
        System.out.println(hoaDon.toString());
        System.out.println("===================");
        HoaDon hoaDon1 = hoaDonService.create(hoaDon);
        hoaDon1.setTrangThai(1);
        HoaDon hoaDon2 = hoaDonService.update(hoaDon1,hoaDon1.getId());
        giaoHang.setHoaDon(hoaDon2);
        giaoHang.setDiaChiGiaoHang(diaChiGiaoHang);
        giaoHang.setPhuongThucGiaoHang("tienmat");
        giaoHang.setDonViVanChuyen("CPN");
        giaoHang.setNgayTao(new Date());
        giaoHang.setNgaySua(new Date());
        LocalDate localDate = LocalDate.now().plusDays(3);
        Date newDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        giaoHang.setNgayDuKienGiao(newDate);
        giaoHang.setTrangThai(1);

        giaoHangService.create(giaoHang);
        for (GioHangChiTietDto ghCt : listGioHangCt) {
            System.out.println(ghCt.toString());
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setId(UUID.randomUUID());
            hoaDonChiTiet.setGiaBan(ghCt.getChiTietSanPham().getGiaBan());
            hoaDonChiTiet.setSoLuong(ghCt.getSoLuong());
            hoaDonChiTiet.setNgayTao(new Date());
            hoaDonChiTiet.setNgaySua(new Date());
            hoaDonChiTiet.setChiTietSanPham(ghCt.getChiTietSanPham());
            hoaDonChiTiet.setHoaDon(hoaDon2);
            hoaDonChiTiet.setTrangThai(1);
            gioHangChiTietRepository.delete(TranferDatas.convertToEntity(ghCt));

            this.hoaDonChiTietService.create(hoaDonChiTiet);
        }
//        String toMail = requestGh.getKhachHang().getEmail();
//        String subject = "Xác nhận đơn hàng thành công";
//        String body = "Cửa hàng MT-Shirt\n" +
//                "\n" +
//                "Xin chào! Đơn hàng của bạn đang được chuẩn bị. Chi tiết đơn hàng của bạn như sau:\n" +
//                "\n" +
//                "THEO DÕI ĐƠN HÀNG [liên kết]\n" +
//                "\n" +
//                "TÓM TẮT ĐƠN HÀNG:\n" +
//                "\n" +
//                "Số đơn hàng: "+hoaDon2.getMa()+"\n" +
//                "Ngày đặt hàng: " + giaoHang.getNgayTao() + "\n" +
//                "Tổng số tiền: "+hoaDon2.getTongTien()+"\n" +
//                "\n" +
//                "ĐỊA CHỈ GIAO HÀNG: "+giaoHang.getDiaChiGiaoHang()+"\n" +
//                "\n" +
//                "DANH SÁCH ĐƠN HÀNG:\n" +
//                "\n";
//
//            // Thêm danh sách sản phẩm
//        body += "| Sản phẩm                | Số lượng | Giá      |\n";
//        body += "|-------------------------|----------|----------|\n";
//
//        for (GioHangChiTiet ghCt : listGioHangCt) {
//            body += "| " + ghCt.getChiTietSanPham().getSanPham().getTen() + "(" + ghCt.getChiTietSanPham().getKichThuoc().getTen()+")" + " | " +
//                    ghCt.getSoLuong() + " | " +
//                    ghCt.getChiTietSanPham().getGiaBan() + " |\n";
//        }
//
//        body += "\nCảm ơn bạn đã mua hàng tại cửa hàng của chúng tôi!\n";
//
//        emailSenderService.sendMail(toMail, subject, body);
//        this.gioHangService.update(gioHang, gioHang.getId());

        String toMail = requestGh.getKhachHang().getEmail();
        String subject = "Xác nhận đơn hàng thành công";

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("orderNumber", hoaDon2.getMa());
        templateModel.put("orderDate", giaoHang.getNgayTao());
        templateModel.put("totalAmount", hoaDon2.getTongTien());
        templateModel.put("deliveryAddress", giaoHang.getDiaChiGiaoHang());
        templateModel.put("trackingUrl", "http://localhost:4200/customer/order-detail/" + giaoHang.getHoaDon().getId());

        List<Map<String, Object>> orderItems = new ArrayList<>();
        for (GioHangChiTietDto ghCt : listGioHangCt) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", ghCt.getChiTietSanPham().getSanPham().getTen() + " (" + ghCt.getChiTietSanPham().getKichThuoc().getTen() + ")");
            item.put("quantity", ghCt.getSoLuong());
            item.put("price", ghCt.getChiTietSanPham().getGiaBan());
            orderItems.add(item);
        }
        templateModel.put("orderItems", orderItems);

        try {
            emailSenderService.sendHtmlMail(toMail, subject, templateModel);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Xử lý lỗi gửi email
        }

        if (voucher != null) {
            int soLuongConLai = voucher.getSoLuong() - 1;
            voucher.setSoLuong(soLuongConLai);
            voucher.setNgaySua(new Date());
            if (soLuongConLai < 0) {
                throw new IllegalArgumentException("Voucher đã hết số lượng");
            } else if (soLuongConLai < 10) {
                System.out.println("Voucher sắp hết số lượng, còn lại: " + soLuongConLai);
            }
            voucherService.update(voucher.getId(), voucher);
        }

    }



}