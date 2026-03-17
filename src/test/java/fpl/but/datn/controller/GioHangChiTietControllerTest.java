package fpl.but.datn.controller;

import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.service.IGioHangChiTietService;
import fpl.but.datn.service.IGioHangService;
import fpl.but.datn.service.impl.GioHangChiTietService;
import fpl.but.datn.service.ICTSanPhamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test add-to-cart APIs (require authentication).
 */
@WebMvcTest(GioHangChiTietController.class)
class GioHangChiTietControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGioHangChiTietService gioHangChiTietService;
    @MockBean
    private IGioHangService gioHangService;
    @MockBean
    private GioHangChiTietService gioHangChiTietService1;
    @MockBean
    private ICTSanPhamService ctSanPhamService;

    @Test
    @WithMockUser
    @DisplayName("POST addProductToCart - with auth and mock service returns 200")
    void addProductToCart_success() throws Exception {
        UUID idGioHang = UUID.randomUUID();
        UUID idSanPham = UUID.randomUUID();
        GioHangChiTiet cartItem = new GioHangChiTiet();
        cartItem.setId(UUID.randomUUID());
        cartItem.setSoLuong(2);

        when(gioHangChiTietService.addProductToGioHang(eq(idGioHang), eq(idSanPham), eq(2)))
                .thenReturn(cartItem);

        mockMvc.perform(post("/api/gio-hang-chi-tiet/addProductToCart")
                        .param("idGioHang", idGioHang.toString())
                        .param("idSanPham", idSanPham.toString())
                        .param("soLuong", "2")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Thêm sản phẩm vào giỏ hàng thành công"))
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    @WithMockUser
    @DisplayName("POST addProductToCartKH - with auth and mock service returns 200")
    void addProductToCartKH_success() throws Exception {
        UUID idGioHang = UUID.randomUUID();
        UUID idSanPham = UUID.randomUUID();
        GioHangChiTiet cartItem = new GioHangChiTiet();
        cartItem.setId(UUID.randomUUID());
        cartItem.setSoLuong(1);

        when(gioHangChiTietService.addProductToGioHangKH(eq(idGioHang), eq(idSanPham), eq(1)))
                .thenReturn(cartItem);

        mockMvc.perform(post("/api/gio-hang-chi-tiet/addProductToCartKH")
                        .param("idGioHang", idGioHang.toString())
                        .param("idSanPham", idSanPham.toString())
                        .param("soLuong", "1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Thêm sản phẩm vào giỏ hàng thành công"));
    }
}
