package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Order;
import com.anonymous.HST1C.Status;

import java.util.List;

public interface OrderRepository {
    int addOrder(Order order);
    Order findOrderByOrdernumber(int ordernumber);
    List<Order> findOrdersByItemid(int itemid);
    List<Order> findOrdersByUsername(String username);
    List<Order> findOrdersByStatus(Status status);
    void updateOrderStatusByOrdernumber(int ordernumber,Status status);
}
