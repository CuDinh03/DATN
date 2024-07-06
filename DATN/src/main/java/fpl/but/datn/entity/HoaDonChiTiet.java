package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JsonBackReference
    private HoaDon hoaDon;
    @ManyToOne
    private ChiTietSanPham chiTietSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private BigDecimal giaBan;
    private Integer trangThai;

}
