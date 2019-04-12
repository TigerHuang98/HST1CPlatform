package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Message;
import com.anonymous.HST1C.Order;
import com.anonymous.HST1C.Role;
import com.anonymous.HST1C.Status;
import com.anonymous.HST1C.data.ItemRepository;
import com.anonymous.HST1C.data.MessageRepository;
import com.anonymous.HST1C.data.OrderRepository;
import com.anonymous.HST1C.data.LoginRepository;
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
    private LoginRepository loginRepository;
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;
    private MessageRepository messageRepository;

    @Autowired
    public StaffListController(LoginRepository loginRepository,ItemRepository itemRepository, OrderRepository orderRepository, MessageRepository messageRepository) {
        this.loginRepository = loginRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.messageRepository = messageRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showClaimList(Model model,HttpSession session){
        if(session.getAttribute("username")==null){
            return "redirect:/";
        }
        if(loginRepository.findLoginByUsername(session.getAttribute("username").toString()).getUid()!= Role.STAFF){//incorrect user login
            return "redirect:/claim_list";
        }
//        if(model.containsAttribute("messages")&&model.containsAttribute("message-linked-ordernumber")) {
//            int message_linked_ordernumber = Integer.parseInt(model.asMap().get("message-linked-ordernumber").toString());
//            Object messagesInModel = model.asMap().get("messages");
//            List<Message> messages = new ArrayList<>();
//            if (messagesInModel instanceof List) {
//                messages = (List<Message>) messagesInModel;
//            }
//            model.addAttribute(messages);
//            model.addAttribute(message_linked_ordernumber);
//        }
        model.addAttribute("orders_processing",orderRepository.findOrdersByStatus(Status.PROCESSING));
        List<Order> doneList=new ArrayList<>();
        doneList.addAll(orderRepository.findOrdersByStatus(Status.APPROVING));
        doneList.addAll(orderRepository.findOrdersByStatus(Status.DENYING));
        model.addAttribute("orders_done",doneList);
        model.addAttribute("hiddenMessageForm",new HiddenMessageForm());
        return "staff_list";
    }
}
