package com.tailor.track.services;

import com.tailor.track.dao.OrderDAO;
import com.tailor.track.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    public Order createOrder(Order order) {
        return orderDAO.createOrder(order);
    }

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public Boolean updateStatus(String orderId) {
        if(orderId.isBlank()) {
            return false;
        }

        Order orderDetail = orderDAO.getOrder(orderId);
        if(orderDetail == null) {
            return false;
        }

        return updateOrderStaus(orderDetail);
    }

    private Boolean updateOrderStaus(Order orderDetail) {
        Order.OrderStatus currentStatus = orderDetail.getOrderStatus();
        Order.OrderStatus nextOrderStatus =  getNextOrderStatus(currentStatus);
        if(nextOrderStatus == null) {
            return null;
        }
        orderDetail.setOrderStatus(nextOrderStatus);
        orderDAO.updateOrder(orderDetail);
        return true;
    }

    private Order.OrderStatus getNextOrderStatus(Order.OrderStatus currentStatus) {
        return Order.OrderStatus.getNext(currentStatus);
    }

    public void deleteOrder(String orderId)
    {
        orderDAO.deleteOrder(orderId);
    }
}
