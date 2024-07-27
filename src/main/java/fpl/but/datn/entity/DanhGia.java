package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DanhGia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ma;
    @ManyToOne
    private KhachHang khachHang;
    @ManyToOne
    private HoaDonChiTiet hoaDonChiTiet;
    private String tieuDe;
    private String noiDung;
    private Integer diem;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")

    private Date ngayTao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")

    private Date ngaySua;
    private Integer trangThai;
}
