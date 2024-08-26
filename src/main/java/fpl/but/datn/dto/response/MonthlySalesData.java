package fpl.but.datn.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlySalesData {
    private int month;
    private long orderCount; // Số lượng hóa đơn
    private BigDecimal totalSales; // Tổng tiền
    private long orderDis; // so hoa don huy

    public MonthlySalesData(int month, long orderCount, BigDecimal totalSales) {
        this.month = month;
        this.orderCount = orderCount;
        this.totalSales = totalSales;
    }

    public MonthlySalesData(int month, long orderDis) {
        this.month =month;
        this.orderDis = orderDis;
    }
}
