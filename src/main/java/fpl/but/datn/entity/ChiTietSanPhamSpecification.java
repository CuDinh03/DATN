package fpl.but.datn.entity;
import fpl.but.datn.entity.ChiTietSanPham;
import org.springframework.data.jpa.domain.Specification;

public class ChiTietSanPhamSpecification {

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
