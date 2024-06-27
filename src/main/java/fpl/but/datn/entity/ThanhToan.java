package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ThanhToan")
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private HoaDon hoaDon;
    @ManyToOne
    private PhuongThucThanhToan phuongThucThanhToan;
    private BigDecimal tienThanhToan;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
}
