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
@Table(name = "ThongKe")
public class ThongKe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    private String ten;
    private Integer giaTri;
    private String moTa;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;
}
