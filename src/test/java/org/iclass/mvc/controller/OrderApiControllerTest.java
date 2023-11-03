package org.iclass.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.iclass.mvc.dto.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class OrderApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;  //자바객체와 json 사이의 직렬화(writeValueAsString 메소드),역직렬화(readValue 메소드)를 위한 객체

    @Test
    void selectOneOrder() throws Exception {
        mockMvc.perform(get("/api/order/{idx}",1001))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("orderApiController 주문하기 - 상태코드 isOk")
    void saveSuccess() throws Exception {
        OrderDto dto =OrderDto.builder()
                .email("momoo@gmail.com")
                .pcode("AHH6_099")
                .quantity(3)
                .orderdate(LocalDate.of(2023,11,1)).build();

        String jsonContent = objectMapper.writeValueAsString(dto);      //직렬화
        mockMvc.perform(post("/api/order")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())     //검증
                .andDo(print());               //출력

    }

    @Test
    @DisplayName("seung0476@gmail.com 회원이 상품코드 AAA_02X 5개 주문 - 상태코드 isOk")
    void saveSuccess2() throws Exception {
        OrderDto dto =OrderDto.builder()
                .email("seung0476@gmail.com")
                .pcode("AAA_02X")
                .quantity(5)
                .orderdate(LocalDate.of(2023,11,25)).build();

        String jsonContent = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/api/order")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("OrderApiController 주문하기 - 상태코드 is4xxClientError")
    void saveFail() throws Exception {
        OrderDto dto =OrderDto.builder()
                .email("momoo@gmail.com")
                .pcode("AHH6_099")
                .quantity(3)
                .orderdate(LocalDate.of(2023,10,31)).build();
        String jsonContent = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/api/order")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());

    }

    @Test
    @DisplayName("sana@gmail.com 회원이 상품코드 LGGRAMT9 7개 주문 - 상태코드 is4xxClientError")
    void saveFail2() throws Exception {
        OrderDto dto =OrderDto.builder()
                .email("sana@gmail.com")
                .pcode("LGGRAMT9")
                .quantity(7)
                .orderdate(LocalDate.of(2023,10,31)).build();
        String jsonContent = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/api/order")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());

    }

    @Test
    @DisplayName("특정 idx 삭제 테스트")
    public void testDelete() throws Exception {
        int orderIdx = 1019;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/order/{idx}", orderIdx))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

}