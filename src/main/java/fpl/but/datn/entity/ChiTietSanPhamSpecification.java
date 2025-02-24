package fpl.but.datn.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamSpecification implements Specification<ChiTietSanPham> {

    private final String keyword;

    public ChiTietSanPhamSpecification(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Predicate toPredicate(Root<ChiTietSanPham> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // Tìm kiếm theo thuộc tính 'ma'
        if (keyword != null && !keyword.trim().isEmpty()) {
            String trimmedKeyword = keyword.trim();
            String pattern = "%" + trimmedKeyword.toLowerCase() + "%";
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("ma")), pattern));

            // Tìm kiếm theo thuộc tính 'sanPham.ten'
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("sanPham").get("ten")), pattern));

            // Tìm kiếm theo thuộc tính 'thuongHieu.ten'
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("thuongHieu").get("ten")), pattern));

            // Tìm kiếm theo thuộc tính 'chatLieu.ten'
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("chatLieu").get("ten")), pattern));

            // Tìm kiếm theo thuộc tính 'danhMuc.ten'
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("danhMuc").get("ten")), pattern));

            // Tìm kiếm theo thuộc tính 'kichThuoc.ten'
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("kichThuoc").get("ten")), pattern));

            // Tìm kiếm theo thuộc tính 'mauSac.ten'
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("mauSac").get("ten")), pattern));

            // Tìm kiếm theo giá trị 'soLuong', 'giaNhap', 'giaBan'
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("soLuong").as(String.class)), pattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("giaNhap").as(String.class)), pattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("giaBan").as(String.class)), pattern));
        }

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }


    public static Specification<ChiTietSanPham> hasMauSac(String mauSac) {
        return (root, query, criteriaBuilder) -> mauSac == null ? null : criteriaBuilder.equal(root.get("mauSac").get("ten"), mauSac);
    }

    public static Specification<ChiTietSanPham> hasKichThuoc(String kichThuoc) {
        return (root, query, criteriaBuilder) -> kichThuoc == null ? null : criteriaBuilder.equal(root.get("kichThuoc").get("ten"), kichThuoc);
    }

    public static Specification<ChiTietSanPham> hasDanhMuc(String danhMuc) {
        return (root, query, criteriaBuilder) -> danhMuc == null ? null : criteriaBuilder.equal(root.get("danhMuc").get("ten"), danhMuc);
    }
}
