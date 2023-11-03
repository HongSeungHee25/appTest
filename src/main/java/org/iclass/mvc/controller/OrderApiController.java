package org.iclass.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.iclass.mvc.dto.OrderDto;
import org.iclass.mvc.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//REST API 구현 컨트롤러
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApiController {
        private final OrderService service;

        @GetMapping("/order/{idx}")
        public OrderDto selectOneOrder(@PathVariable int idx){
            return service.selectByidx(idx);
        }

        @PostMapping("/order")
        public Map<String,Object> save(@RequestBody @Valid OrderDto dto){
            service.insert(dto);
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("message","주문처리가 완료 되었습니다.");
            return resultMap;
        }

        @GetMapping("/orders/{email}")
        public List<OrderDto> selectByEmail(@PathVariable String email){
            return service.selectByEmail(email);
        }

        @DeleteMapping("/order/{idx}")
        public Map<String,Object> delete(@PathVariable int idx){
            service.delete(idx);
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("message","주문처리 취소가 완료 되었습니다.");
            return resultMap;
        }

}
