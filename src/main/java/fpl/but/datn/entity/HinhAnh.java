package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class HinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private ChiTietSanPham chiTietSanPham;

    @ManyToOne
    private SanPham sanPham;
    private String ma;
    private String url;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;

}
