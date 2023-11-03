package org.iclass.mvc.dao;

import lombok.extern.slf4j.Slf4j;
import org.iclass.mvc.dto.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class OrderMapperTest {

    @Autowired
    private OrderMapper mapper;

    @Test
    @DisplayName("주문한 회원의 이메일을 조회합니다.(중복제거)")
    void selectByEmail() {
        List<String> emails = mapper.selectOrderByEmail();
        log.info("주문 회원 이메일 : {}",emails);
        assertNotNull(emails);
    }

    @Test
    @DisplayName("Order테이블에 새롭게 Insert를 합니다.")
    void insert() {

        OrderDto dto =OrderDto.builder()
                .email("momoo@gmail.com")
                .pcode("AHH6_099")
                .quantity(3)
                .orderdate(LocalDate.of(2023,10,11)).build();
        mapper.insert(dto);
        int idx = dto.getIdx();     //selectKey로 생성된 시퀀스값 가져오기
        OrderDto result = mapper.selectByIdx(idx);
        log.info(" inserted OrderDto : {}",result);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Order테이블에 새롭게 Insert를 합니다.")
    void insert2() {

        OrderDto dto =OrderDto.builder()
                .email("seung0476@gmail.com")
                .pcode("AXC__02g")
                .quantity(5)
                .orderdate(LocalDate.of(2023,10,31)).build();
        mapper.insert(dto);
        int idx = dto.getIdx();     //selectKey로 생성된 시퀀스값 가져오기
        OrderDto result = mapper.selectByIdx(idx);
        log.info(" inserted OrderDto : {}",result);
        assertNotNull(result);
    }


    @Test
    @DisplayName("이메일에 따라 Order 목록을 가져오는 테스트")
    void testSelectOrderByEmail() {
        String email = "seung0476@gmail.com";
        List<OrderDto> result = mapper.selectByEmail(email);
        assertNotNull(result);
    }
    @Test
    @DisplayName("인덱스에 따라 OrderDto를 가져오는 테스트")
    void testSelectByIdx() {
        int idx = 1001;
        OrderDto result = mapper.selectByIdx(idx);
        assertNotNull(result);
    }

    @Test
    @DisplayName("인덱스에 따라 Order 삭제하는 테스트")
    void testDelete() {
        int idx = 1012;
        mapper.delete(idx);

        OrderDto result = mapper.selectByIdx(idx);

        assertNull(result, "데이터가 삭제되었으므로 null이어야 합니다.");
    }

}