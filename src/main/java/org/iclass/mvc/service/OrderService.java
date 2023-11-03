package org.iclass.mvc.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.iclass.mvc.dao.OrderMapper;
import org.iclass.mvc.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderMapper dao;

    public List<String> selectOrderByEmail() {
        return dao.selectOrderByEmail();
    }

    public int insert(OrderDto dto){
        return dao.insert(dto);
    }

    public List<OrderDto> selectByEmail(String email){
        return dao.selectByEmail(email);
    }

    public OrderDto selectByidx(int idx){
        return dao.selectByIdx(idx);
    }

    public void delete(int idx) {
        dao.delete(idx);
    }
}
