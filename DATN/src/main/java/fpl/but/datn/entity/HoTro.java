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
@Table(name = "HoTro")
public class HoTro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String tieuDe;
    private String noiDung;
    private Date ngayTao;
    private boolean trangThai;
}
