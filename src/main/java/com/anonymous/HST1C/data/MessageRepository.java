package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Message;

import java.util.List;

public interface MessageRepository {
    int addMessage(Message message);
    Message findMessageById(int messageid);
    List<Message> findMessagesByOrdernumber(int ordernumber);
}
