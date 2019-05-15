package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Message;
import com.anonymous.HST1C.Role;
import com.anonymous.HST1C.Userinfo;
import com.anonymous.HST1C.data.MessageRepository;
import com.anonymous.HST1C.data.OrderRepository;
import com.anonymous.HST1C.data.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/fetch_message")
public class FetchMessagesController {
    private MessageRepository messageRepository;
    private OrderRepository orderRepository;
    private UserinfoRepository userinfoRepository;

    @Autowired
    public FetchMessagesController(MessageRepository messageRepository, OrderRepository orderRepository, UserinfoRepository userinfoRepository) {
        this.messageRepository = messageRepository;
        this.orderRepository = orderRepository;
        this.userinfoRepository = userinfoRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fetchMessages(HiddenMessageForm hiddenMessageForm, RedirectAttributes model){
        List<Message> messages=new ArrayList<>();
        if(hiddenMessageForm!=null){
            //should check role if this Controller is no longer an internal Controller,
            //i.e. it only redirect to other page.
            if(hiddenMessageForm.getText()!=null){
                Message m=new Message(
                        -1,
                        hiddenMessageForm.getText(),
                        new Timestamp(System.currentTimeMillis()),
                        hiddenMessageForm.getOrdernumber(),
                        hiddenMessageForm.getRole()==Role.CUSTOMER
                );
                messageRepository.addMessage(m);
            }
            messages.addAll(messageRepository.findMessagesByOrdernumber(hiddenMessageForm.getOrdernumber()));
            if(hiddenMessageForm.getRole()== Role.STAFF) {
                if(messages.size()!=0){
                    Userinfo userinfo=userinfoRepository.findUserinfo(
                            orderRepository.findOrderByOrdernumber(
                                    messageRepository.findMessageById(
                                            messages.get(0).getMessageid()
                                    ).getOrdernumber()
                            ).getUsername()
                    );
                    if(userinfo!=null){
                        byte[] userIconBytes = userinfo.getIconBytes();
                        String userIconBase64 = Base64.getEncoder().encodeToString(userIconBytes);
                        model.addFlashAttribute("user_pic",userIconBase64);
                    }
                }
                for (Message message : messages) {
                    message.setIssend(!message.isSend());
                }
            }
            model.addFlashAttribute("messages",messages);
            model.addFlashAttribute("message_linked_ordernumber",hiddenMessageForm.getOrdernumber());
            if(hiddenMessageForm.getRole()==Role.STAFF){
                return "redirect:/staff_list";
            }
        }
        return "redirect:/claim_detail";
    }
}
