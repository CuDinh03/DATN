package fpl.but.datn.service.impl;

import fpl.but.datn.dto.request.ChiTietSanPhamDto;
import fpl.but.datn.dto.request.FilterSanPhamRequest;
import fpl.but.datn.dto.request.HinhAnhRequest;
import fpl.but.datn.entity.*;
import fpl.but.datn.mapper.ChiTietSanPhamMapper;
import fpl.but.datn.repository.CTSanPhamRepository;
import fpl.but.datn.repository.HinhAnhRepository;
import fpl.but.datn.service.ICTSanPhamService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CTSanPhamService implements ICTSanPhamService {

    @Autowired
    private CTSanPhamRepository ctSanPhamRepository;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return ctSanPhamRepository.findByNgayTao();
    }

    @Override
    public ChiTietSanPham create(ChiTietSanPham chiTietSanPham) {
        return null;
    }

    @Override
    public Page<ChiTietSanPham> getAllChiTietSanPhamPageableSapXepNGayTao(Pageable pageable) {
        return ctSanPhamRepository.findAllSapXepNgayTao(pageable);
    }

    @Override
    public Page<ChiTietSanPham> getAllChiTietSanPhamPageable(Pageable pageable) {
        return ctSanPhamRepository.findAllSapXepNgayTao(pageable);
    }



    @Override
    public List<MauSac> findAllMauSacByMaCTSP(String maChiTietSanPham) {
        return ctSanPhamRepository.findMauSacsByMaSanPhamChiTiet(maChiTietSanPham);
    }

    @Override
    public List<KichThuoc> findkichThuocsByMaSanPhamChiTiet(String maChiTietSanPham) {
        return ctSanPhamRepository.findkichThuocsByMaSanPhamChiTiet(maChiTietSanPham);
    }

    @Override
    public ChiTietSanPham findChiTietSanPhamByMauSacAndKichThuoc(String ma, UUID kichThuoc, UUID mauSac) {
        return ctSanPhamRepository.findChiTietSanPhamByMauSacAndKichThuoc(ma, kichThuoc, mauSac);
    }

    @Override
    public List<ChiTietSanPham> findSanPhamByKichThuoc(String ma, UUID kichThuoc) {
        return ctSanPhamRepository.findChiTietSanPhamByMaAndKichThuoc(ma, kichThuoc);
    }

    @Override
    public List<ChiTietSanPham> findCTSPBySanPhamId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByChatLieuId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByDanhMucId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByKichThuocId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByMauSacId(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> findCTSPByThuongHieuId(UUID id) {
        return null;
    }


//    @Override

    @Override
    public ChiTietSanPham create(ChiTietSanPham request, List<HinhAnhRequest> hinhAnhs) {


        // Kiểm tra xem sản phẩm chi tiết đã tồn tại chưa
        long count = ctSanPhamRepository.countByCriteria(
                request.getMa(),
                request.getSanPham(),
                request.getThuongHieu(),
                request.getChatLieu(),
                request.getDanhMuc(),
                request.getKichThuoc(),
                request.getMauSac());

        if (count > 0) {
            // Nếu sản phẩm chi tiết đã tồn tại, không làm gì cả và trả về null
            return null;
        } else {
            // Tạo mới sản phẩm chi tiết
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setMa(request.getMa());
            chiTietSanPham.setSanPham(request.getSanPham());
            chiTietSanPham.setThuongHieu(request.getThuongHieu());
            chiTietSanPham.setChatLieu(request.getChatLieu());
            chiTietSanPham.setDanhMuc(request.getDanhMuc());
            chiTietSanPham.setKichThuoc(request.getKichThuoc());
            chiTietSanPham.setMauSac(request.getMauSac());
            chiTietSanPham.setSoLuong(request.getSoLuong());
            chiTietSanPham.setGiaNhap(request.getGiaNhap());
            chiTietSanPham.setGiaBan(request.getGiaBan());
            chiTietSanPham.setNgayNhap(new Date());
            chiTietSanPham.setNgayTao(new Date());
            chiTietSanPham.setNgaySua(new Date());
            chiTietSanPham.setTrangThai(request.getTrangThai());
            chiTietSanPham.setHinhAnh(request.getHinhAnh());

            ChiTietSanPham chiTietSanPham1 = ctSanPhamRepository.save(chiTietSanPham);

            for (HinhAnhRequest hinhAnh : hinhAnhs) {
                if (chiTietSanPham1.getMa().equals(hinhAnh.getMa())) {
                    HinhAnh hinhAnh1 = new HinhAnh();
                    Random random = new Random();
                    hinhAnh1.setMa("HA" + random.nextInt(1000));
                    hinhAnh1.setUrl(hinhAnh.getUrl());
                    hinhAnh1.setId(UUID.randomUUID());
                    hinhAnh1.setChiTietSanPham(chiTietSanPham1);
                    hinhAnh1.setTrangThai(1);
                    hinhAnhRepository.save(hinhAnh1);
                }
            }
            Random random = new Random();
            chiTietSanPham1.setMa("CTSP" + random.nextInt(1000));
            // Lưu sản phẩm chi tiết
            return ctSanPhamRepository.saveAndFlush(chiTietSanPham1);
        }
    }


    @Override
    public ChiTietSanPham update(ChiTietSanPham request, UUID id) {

        ChiTietSanPham chiTietSanPhamoldValue = ctSanPhamRepository.findById(id).get();
        chiTietSanPhamoldValue.setMa(request.getMa());
        chiTietSanPhamoldValue.setSanPham(request.getSanPham());
        chiTietSanPhamoldValue.setChatLieu(request.getChatLieu());
        chiTietSanPhamoldValue.setDanhMuc(request.getDanhMuc());
        chiTietSanPhamoldValue.setKichThuoc(request.getKichThuoc());
        chiTietSanPhamoldValue.setMauSac(request.getMauSac());
        chiTietSanPhamoldValue.setSoLuong(request.getSoLuong());
        chiTietSanPhamoldValue.setGiaNhap(request.getGiaNhap());
        chiTietSanPhamoldValue.setGiaBan(request.getGiaBan());
        chiTietSanPhamoldValue.setNgaySua(new Date());
        chiTietSanPhamoldValue.setHinhAnh(request.getHinhAnh());
        return ctSanPhamRepository.save(chiTietSanPhamoldValue);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<ChiTietSanPham> optional = ctSanPhamRepository.findById(id);
        if (optional.isPresent()) {
            ChiTietSanPham chiTietSanPham = optional.get();
            chiTietSanPham.setNgaySua(new Date());
            chiTietSanPham.setTrangThai(0);
            ctSanPhamRepository.save(chiTietSanPham);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ChiTietSanPham findById(UUID id) {
        return ctSanPhamRepository.findById(id).get();
    }


    @Override
    @Transactional
    public boolean updateTrangThai(UUID id) {
        int updatedRecords = ctSanPhamRepository.updateTrangThai(id);
        return updatedRecords > 0;
    }

    public List<ChiTietSanPham> saveCtsp(SanPham sanPham,
                                         List<MauSac> mauSacList,
                                         ChatLieu chatLieu,
                                         DanhMuc danhMuc,
                                         ThuongHieu thuongHieu,
                                         List<KichThuoc> kichThuocList) {
        List<ChiTietSanPham> chiTietSanPhamsList = new ArrayList<>();
        for (MauSac ms : mauSacList) {
            for (KichThuoc kt : kichThuocList) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                Random random = new Random();
                chiTietSanPham.setMa("CTSP" + random.nextInt(1000));
                chiTietSanPham.setChatLieu(chatLieu);
                chiTietSanPham.setThuongHieu(thuongHieu);
                chiTietSanPham.setDanhMuc(danhMuc);
                chiTietSanPham.setSanPham(sanPham);
                chiTietSanPham.setMauSac(ms);
                chiTietSanPham.setKichThuoc(kt);
                chiTietSanPham.setNgayNhap(new Date());
                chiTietSanPham.setNgayTao(new Date());
                chiTietSanPham.setNgaySua(new Date());
                chiTietSanPham.setTrangThai(2);
                chiTietSanPhamsList.add(create(chiTietSanPham));
            }
        }

        return chiTietSanPhamsList;

    }

    @Override
    public List<ChiTietSanPham> getCtsp() {
        return ctSanPhamRepository.getAllByTrangThai(2);
    }

    @Override
    public List<ChiTietSanPham> saveListCt(List<ChiTietSanPham> list) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> saveListCt(List<ChiTietSanPham> list, List<HinhAnhRequest> hinhAnhs) {
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
        for (ChiTietSanPham ct :
                list) {
            ct.setNgaySua(new Date());
            ct.setTrangThai(1);
            chiTietSanPhamList.add(create(ct, hinhAnhs));
        }
        return chiTietSanPhamList;
    }

    @Override
    public List<ChiTietSanPham> findByFilter(UUID mauSac, UUID kichThuoc, UUID danhMuc) {
        return ctSanPhamRepository.findByFilter(mauSac, kichThuoc, danhMuc);
    }



    public Page<ChiTietSanPham> filterSanPham(FilterSanPhamRequest request, int page, int size) {
        Specification<ChiTietSanPham> spec = Specification.where(null);

        if (request.getMauSac() != null) {
            spec = spec.and(ChiTietSanPhamSpecification.hasMauSac(request.getMauSac().getTen()));
        }

        if (request.getKichThuoc() != null) {
            spec = spec.and(ChiTietSanPhamSpecification.hasKichThuoc(request.getKichThuoc().getTen()));
        }

        if (request.getDanhMuc() != null) {
            spec = spec.and(ChiTietSanPhamSpecification.hasDanhMuc(request.getDanhMuc().getTen()));
        }

        Pageable pageable = PageRequest.of(page, size);

        return ctSanPhamRepository.findAll(spec, pageable);
    }

    @Override
    public ChiTietSanPham getByMKS(UUID sanPhamId, UUID kichThuocId, UUID mauSacId) {
        return ctSanPhamRepository.getByMKS(sanPhamId,kichThuocId,mauSacId);
    }

    @Autowired
    private ChiTietSanPhamMapper chiTietSanPhamMapper;

    @Override
    public Page<ChiTietSanPhamDto> search(String keyword, Pageable pageable) {
        Page<ChiTietSanPham> result = ctSanPhamRepository.findByKeyword(keyword, pageable);
        return result.map(chiTietSanPhamMapper::toDto);
    }
}
