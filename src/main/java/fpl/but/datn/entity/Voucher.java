package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    private String ten;
    private String loaiGiamGia;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private BigDecimal giaTriGiam;
    private BigDecimal giaTriToiThieu;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;
}
