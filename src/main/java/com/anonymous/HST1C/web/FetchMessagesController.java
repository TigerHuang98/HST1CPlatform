package com.anonymous.HST1C.web;

import com.anonymous.HST1C.Message;
import com.anonymous.HST1C.data.MessageRepository;
import com.anonymous.HST1C.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fetch_message")
public class FetchMessagesController {//for staff
    private MessageRepository messageRepository;

    @Autowired
    public FetchMessagesController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fetchMessages(HiddenMessageForm hiddenMessageForm, RedirectAttributes model){
        List<Message> messages=new ArrayList<>();
        if(hiddenMessageForm!=null){
            messages.addAll(messageRepository.findMessagesByOrdernumber(hiddenMessageForm.getOrdernumber()));
            for (Message message:messages){
                message.setIssend(!message.isSend());
            }
            model.addFlashAttribute("messages",messages);
            model.addFlashAttribute("message_linked_ordernumber",hiddenMessageForm.getOrdernumber());
        }
        return "redirect:/staff_list";
    }
}
