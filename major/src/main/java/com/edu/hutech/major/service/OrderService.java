package com.edu.hutech.major.service;

import com.edu.hutech.major.dto.OrderDTO;
import com.edu.hutech.major.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<Order> getAllOrder();
    void updateOrder(Order Order);
    void removeOrderById(long id);
    Optional<Order> getOrderById(long id);
    boolean saveBill(OrderDTO dto);
}
