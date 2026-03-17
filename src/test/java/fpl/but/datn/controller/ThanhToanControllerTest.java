package fpl.but.datn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fpl.but.datn.dto.request.ThanhToanOnl;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.service.impl.GioHangService;
import fpl.but.datn.service.impl.HoaDonChiTietService;
import fpl.but.datn.service.impl.HoaDonGioHangService;
import fpl.but.datn.service.impl.HoaDonService;
import fpl.but.datn.service.impl.ThanhToanService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test for purchase and payment flow.
 * POST /api/thanhtoan/onl is public (no auth required).
 */
@WebMvcTest(ThanhToanController.class)
@ContextConfiguration(classes = {ThanhToanController.class})
class ThanhToanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HoaDonService hoaDonService;
    @MockBean
    private HoaDonChiTietService hoaDonChiTietService;
    @MockBean
    private GioHangService gioHangService;
    @MockBean
    private HoaDonGioHangService hoaDonGioHangService;
    @MockBean
    private ThanhToanService thanhToanService;

    @Test
    @DisplayName("POST /api/thanhtoan/onl - invalid payload (null gioHang) returns error message")
    void thanhtoanOnl_invalidPayload_returnsError() throws Exception {
        ThanhToanOnl body = new ThanhToanOnl();
        body.setGioHang(null);
        body.setTongTien(BigDecimal.valueOf(100));
        body.setTongTienGiam(BigDecimal.ZERO);
        body.setGioHangChiTietList(List.of());

        mockMvc.perform(post("/api/thanhtoan/onl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Thông tin thanh toán không hợp lệ"));
    }

    @Test
    @DisplayName("POST /api/thanhtoan/onl - valid payload calls service and returns success")
    void thanhtoanOnl_validPayload_returnsSuccess() throws Exception {
        UUID gioHangId = UUID.randomUUID();
        UUID ctspId = UUID.randomUUID();
        GioHang gioHang = new GioHang();
        gioHang.setId(gioHangId);
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setId(ctspId);
        ctsp.setGiaBan(BigDecimal.valueOf(50));

        ThanhToanOnl body = new ThanhToanOnl();
        body.setGioHang(gioHang);
        body.setTongTien(BigDecimal.valueOf(100));
        body.setTongTienGiam(BigDecimal.ZERO);
        body.setDiaChiGiaoHang("123 Test Street");
        body.setGhiChu("");
        body.setVoucher(null);
        var dto = new fpl.but.datn.dto.request.GioHangChiTietDto();
        dto.setSoLuong(1);
        dto.setChiTietSanPham(ctsp);
        body.setGioHangChiTietList(List.of(dto));

        doNothing().when(thanhToanService).thanhToanSanPhamOnline(
                any(GioHang.class), any(BigDecimal.class), any(BigDecimal.class),
                any(), anyString(), anyString(), anyList());

        mockMvc.perform(post("/api/thanhtoan/onl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Thanh toán thành công"))
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    @DisplayName("POST /api/thanhtoan/onl - service throws AppException returns error in message")
    void thanhtoanOnl_serviceThrows_returnsErrorInBody() throws Exception {
        UUID gioHangId = UUID.randomUUID();
        GioHang gioHang = new GioHang();
        gioHang.setId(gioHangId);
        var dto = new fpl.but.datn.dto.request.GioHangChiTietDto();
        dto.setSoLuong(1);
        dto.setChiTietSanPham(new ChiTietSanPham());

        ThanhToanOnl body = new ThanhToanOnl();
        body.setGioHang(gioHang);
        body.setTongTien(BigDecimal.valueOf(100));
        body.setTongTienGiam(BigDecimal.ZERO);
        body.setGioHangChiTietList(List.of(dto));

        doThrow(new RuntimeException("GIO_HANG_NOT_FOUND"))
                .when(thanhToanService).thanhToanSanPhamOnline(any(), any(), any(), any(), anyString(), anyString(), anyList());

        mockMvc.perform(post("/api/thanhtoan/onl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("lỗi")));
    }
}
