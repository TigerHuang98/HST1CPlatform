package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Order;

import java.util.List;

public interface OrderRepository {
    int addOrder(Order order);
    Order findOrderByOrdernumber(int ordernumber);
    List<Order> findOrdersByUsername(String username);
}
