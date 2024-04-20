package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "GioHangChiTiet")
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "id_gh")
    private GioHang idGioHang;
    @ManyToOne
    @JoinColumn(name = "id_sp")
    private SanPhamChiTiet idSP;
    private Integer soLuong;
    private boolean trangThai;
}
