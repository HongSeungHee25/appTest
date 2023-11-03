package org.iclass.mvc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.dto.OrderDto;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<String> selectOrderByEmail();
    int insert(OrderDto dto);
    List<OrderDto> selectByEmail(String email);
    OrderDto selectByIdx(int idx);
    void delete(int idx);

}
