package org.iclass.mvc.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc       //MockMvc 를 위한 설정
class OrderControllerTest {
// 테스트에 필요한 기능만 가지는 가짜 객체를 만들어서 애플리케이션 서버에 배포하지 않고도 스프링 MVC 동작을 재현할 수 있는 클래스
// WebApplicationContext를 로드하며, 내장된 서블릿 컨테이너가 아닌 Mock 서블릿을 제공한다.
    @Autowired
    private MockMvc mvc;

    @Test
    void form() throws Exception {

        mvc.perform(get("/order/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/new"))
                .andDo(print());
    }

    @Test
    void save() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", "abc@abcxyz.com");
        map.add("pcode", "AAA_02X");
        map.add("quantity", "3");
        map.add("orderdate", "2023-11-12");

        mvc.perform(MockMvcRequestBuilders.post("/order/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(map))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/order/confirm?idx=1013"))
                .andDo(print());
    }

    @Test
    @DisplayName("새롭게 Insert 하는 Test")
    void Ordersave() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", "seung0476@naver.com");
        map.add("pcode", "LGGRAMT9");
        map.add("quantity", "7");
        map.add("orderdate", "2023-12-25");

        mvc.perform(MockMvcRequestBuilders.post("/order/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(map))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/order/confirm?idx=1021"))
                .andDo(print());
    }
}