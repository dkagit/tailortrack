package com.tailor.track.dao;

import com.tailor.track.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO {
    @Autowired
    MongoTemplate mongoTemplate;

    public Order createOrder(Order order) {
        return mongoTemplate.save(order);
    }

    public List<Order> getAllOrders() {
        return mongoTemplate.findAll(Order.class);
    }

    public Order getOrder(String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));
        return mongoTemplate.findOne(query, Order.class);
    }

    public void updateOrder(Order orderDetail) {
        mongoTemplate.save(orderDetail, "orders");
    }
    public void deleteOrder(String orderId){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));
        mongoTemplate.remove(query, Order.class);
    }
}
