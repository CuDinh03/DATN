package fpl.but.datn.entity;

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
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private HoaDon idHoaDon;

    @ManyToOne
    private ChiTietSanPham idSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private BigDecimal giaBan;
    private Boolean trangThai;
}
