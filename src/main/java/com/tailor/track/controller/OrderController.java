package com.tailor.track.controller;

import com.tailor.track.models.Order;
import com.tailor.track.services.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order orderDetails = orderService.createOrder(order);
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderService.getAllOrders();
        return ResponseEntity.ok(orderList);
    }

    @PutMapping("/update")
    public ResponseEntity<String> changeNextStatus(@RequestParam String orderId) {
        Boolean status = orderService.updateStatus(orderId);
        if(status == null || status.equals(false)) {
            return ResponseEntity.badRequest().body("Invalid Order Id");
        }
        return ResponseEntity.ok("Order status udpated");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeOrder(@RequestParam String orderId){
        if(StringUtils.isBlank(orderId)){
            ResponseEntity.badRequest().body("Invalid Order ID");
        }
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order Deleted");
    }
}
