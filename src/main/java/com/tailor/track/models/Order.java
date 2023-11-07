package com.tailor.track.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "orders")
public class Order {
    private String id;
    private String userId;
    private Float cost;
    private OrderStatus orderStatus;

    public enum OrderStatus {
        ORDER_RECEIVED,
        IN_PROGRESS,
        COMPLETED,
        DELIVERED;

        public static OrderStatus getNext(OrderStatus currentStatus) {
            if(OrderStatus.DELIVERED.equals(currentStatus)) {
                return currentStatus;
            }
            boolean nextStatusNotFound = true;
            for (OrderStatus status: values()) {
                if(status.equals(currentStatus)) {
                    nextStatusNotFound = false;
                    continue;
                }
                if(!nextStatusNotFound) {
                    return status;
                }
            }
            return null;
        }
    }
}
