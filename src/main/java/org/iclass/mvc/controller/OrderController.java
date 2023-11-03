package org.iclass.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.OrderDto;
import org.iclass.mvc.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @GetMapping("/new")
    public void newForm(){
    }

    @PostMapping("/new")
    public String save(@Valid OrderDto dto, RedirectAttributes re){
        service.insert(dto);
        int idx = dto.getIdx();
        re.addAttribute("idx",idx);
        return "redirect:/order/confirm";
    }

    @GetMapping("/confirm")
    public void confirm(int idx, Model model){
        OrderDto vo = service.selectByidx(idx);
        model.addAttribute("vo",vo);
    }

    @GetMapping("/emailList")
    public void emailList(Model model){
        List<String> emails = service.selectOrderByEmail();
        model.addAttribute("list",emails);
    }

    @GetMapping("/detail")
    public void detail(String email,Model model){
        List<OrderDto> list = service.selectByEmail(email);
        model.addAttribute("email",email);
        model.addAttribute("list",list);
    }

    @PostMapping("/delete")
    public void delete(int idx){
        service.delete(idx);
    }
}

