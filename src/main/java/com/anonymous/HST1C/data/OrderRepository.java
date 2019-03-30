package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Order;

public interface OrderRepository {
    Order addOrder(Order order);
    Order findOrderByOrdernumber(int ordernumber);
}
