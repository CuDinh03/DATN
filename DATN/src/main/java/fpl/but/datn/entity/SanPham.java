package fpl.but.datn.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ten;
    private String ma;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;

//  @JsonCreator và @JsonProperty, bạn cần cấu hình SanPham để có thể được khởi tạo từ UUID.
    @JsonCreator
    public SanPham(@JsonProperty("id") UUID id) {
        this.id = id;
    }
}
