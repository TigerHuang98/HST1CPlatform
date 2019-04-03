package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Status;
import com.anonymous.HST1C.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/staff_handle")
public class StaffHandleController {
    private OrderRepository orderRepository;

    @Autowired
    public StaffHandleController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequests(
            @RequestParam(value = "ordernumber",defaultValue = "0") int ordernumber,
            @RequestParam(value = "act",defaultValue = "none") String action
    ){
      if (ordernumber==0|| action.equals("none")){
          return "redirect:/staff_list";
      }
      switch (action){
          case "approve":
              orderRepository.updateOrderStatusByOrdernumber(ordernumber, Status.APPROVING);
              break;
          case "deny":
              orderRepository.updateOrderStatusByOrdernumber(ordernumber,Status.DENYING);
              break;
      }
        return "redirect:/staff_list";
    }
}
