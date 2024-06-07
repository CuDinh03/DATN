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

public class GioHangHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Date ngayTao;
    private Date ngaySua;

    @ManyToOne
    private GioHang gioHang;

    @ManyToOne
    private HoaDon hoaDon;

}
