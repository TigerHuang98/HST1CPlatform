package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Item;
import com.anonymous.HST1C.Order;
import com.anonymous.HST1C.Status;
import com.anonymous.HST1C.data.ItemRepository;
import com.anonymous.HST1C.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

@Controller
@RequestMapping("/claim_form")
public class ClaimFormController {
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;
    @Autowired
    public ClaimFormController(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }
    @RequestMapping(method = RequestMethod.POST)
    public String processClaim(ClaimForm claimForm, HttpSession session) throws IOException, SQLException {
        Object usernameObject=session.getAttribute("username");
        String username=null;
        if(usernameObject!=null){
            username=usernameObject.toString();
        }else{
            return "home";//TODO:login page
        }
        Item item=new Item(
                -1,
                new SerialBlob(claimForm.getPicture().getBytes()),
                claimForm.getPrice(),
                claimForm.getItemname(),
                username,
                claimForm.getDescription());
        int itemid=itemRepository.addItem(item);
        Order order=new Order(
                -1,
                new Timestamp(System.currentTimeMillis()),
                username,
                itemid,
                Status.PROCESSING,
                claimForm.getLostdate()
        );
        orderRepository.addOrder(order);
//        redirect.addFlashAttribute("username",username);
        return "redirect:/claim_list";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String showClaimForm(Model model){
//        if(model.containsAttribute("username")){
//            String username=model.asMap().get("username").toString();//TODO:maybe add checking logic?
//            redirect.addAttribute("username",username);
//        }
        model.addAttribute(new ClaimForm());
        return "claim_form";
    }
}
