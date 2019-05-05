package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Item;
import com.anonymous.HST1C.Order;
import com.anonymous.HST1C.Role;
import com.anonymous.HST1C.Userinfo;
import com.anonymous.HST1C.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@Controller
@RequestMapping("/claim_detail")

public class ClaimDetailController {
    private LoginRepository loginRepository;
    private UserinfoRepository userinfoRepository;
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;
    private MessageRepository messageRepository;

    @Autowired
    public ClaimDetailController(LoginRepository loginRepository, UserinfoRepository userinfoRepository, ItemRepository itemRepository, OrderRepository orderRepository, MessageRepository messageRepository) {
        this.loginRepository = loginRepository;
        this.userinfoRepository = userinfoRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showClaimDetail(Model model, HttpSession session){
        if(session.getAttribute("username")==null){//no login info
            return "redirect:/";
        }
        if(loginRepository.findLoginByUsername(session.getAttribute("username").toString()).getUid()!= Role.CUSTOMER){//incorrect user login
            return "redirect:/";
        }
        int ordernumber;
        if(model.containsAttribute("message_linked_ordernumber")){//if model does not contain order number, than customer will be return to the claim list
            ordernumber=Integer.parseInt(model.asMap().get("message_linked_ordernumber").toString());
        }else{
            return "redirect:/claim_list";
        }
        Order order=orderRepository.findOrderByOrdernumber(ordernumber);
        if(order==null){
            return "redirect:/claim_list";
        }
        if(!order.getUsername().equals(session.getAttribute("username"))){
            //this order does NOT belongs to the user, potential attack happened!
            order=null;
            return "redirect:/claim_list";
        }
        Item item=itemRepository.findItemById(order.getItemid());
        if(item==null){
            return "redirect:/claim_list";
        }
        Userinfo userinfo=userinfoRepository.findUserinfo(session.getAttribute("username").toString());
        if(userinfo!=null){
            byte[] userIconBytes = userinfo.getIconBytes();
            String userIconBase64 = Base64.getEncoder().encodeToString(userIconBytes);
            model.addAttribute("user_pic",userIconBase64);
        }
        byte[] imageBytes = item.getPictureBytes();
        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        model.addAttribute("itemPicture", imageBase64);
        model.addAttribute("item",item);
        model.addAttribute("order",order);
        return "claim_detail";
    }

}
