package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Order;
import com.anonymous.HST1C.Status;
import com.anonymous.HST1C.data.ItemRepository;
import com.anonymous.HST1C.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staff_list")

public class StaffListController {
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public StaffListController(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showClaimList(Model model){
        model.addAttribute("orders_processing",orderRepository.findOrdersByStatus(Status.PROCESSING));
        List<Order> doneList=new ArrayList<>();
        doneList.addAll(orderRepository.findOrdersByStatus(Status.APPROVING));
        doneList.addAll(orderRepository.findOrdersByStatus(Status.DENYING));
        model.addAttribute("orders_done",doneList);
        return "staff_list";
    }
}
