package fpl.but.datn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChucVu {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    private String ten;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;

}