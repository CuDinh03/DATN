package fpl.but.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlySalesData {
    private int month;
    private long orderCount; // Số lượng hóa đơn
    private BigDecimal totalSales; // Tổng tiền

}
