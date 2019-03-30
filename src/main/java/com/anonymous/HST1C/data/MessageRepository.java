package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Message;

public interface MessageRepository {
    Message addMessage(Message message);
    Message findMessageById(int messageid);
    Message[] findMessageByOrdernumber(int ordernumber);
}
