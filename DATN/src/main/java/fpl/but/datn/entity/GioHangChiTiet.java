package fpl.but.datn.entity;

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
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_gio_hang")
    private GioHang idGioHang;
    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    private ChiTietSanPham idSanPham;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;
}
