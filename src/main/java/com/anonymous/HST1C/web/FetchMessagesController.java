package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Message;
import com.anonymous.HST1C.Role;
import com.anonymous.HST1C.data.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fetch_message")
public class FetchMessagesController {
    private MessageRepository messageRepository;

    @Autowired
    public FetchMessagesController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fetchMessages(HiddenMessageForm hiddenMessageForm, RedirectAttributes model){
        List<Message> messages=new ArrayList<>();
        if(hiddenMessageForm!=null){
            //TODO:check role?
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
        return "redirect:/claim_list";
    }
}
