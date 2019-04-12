package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Order;
import com.anonymous.HST1C.data.ItemRepository;
import com.anonymous.HST1C.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/claim_list")
public class ClaimListController {
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public ClaimListController(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showClaimList(Model model,HttpSession session){
        if(session.getAttribute("username")==null){
            return "redirect:/";//TODO:login page
        }
        model.addAttribute("orders",orderRepository.findOrdersByUsername(session.getAttribute("username").toString()));//TODO:add null attribute?
        model.addAttribute("hiddenMessageForm",new HiddenMessageForm());
        return "claim_list";
    }
}
