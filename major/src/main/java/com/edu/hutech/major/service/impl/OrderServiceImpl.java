package com.edu.hutech.major.service.impl;

import com.edu.hutech.major.dto.OrderDTO;
import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.model.OrderDetail;
import com.edu.hutech.major.model.Product;
import com.edu.hutech.major.model.User;
import com.edu.hutech.major.repository.OrderDetailRepository;
import com.edu.hutech.major.repository.OrderRepository;
import com.edu.hutech.major.repository.UserRepository;
import com.edu.hutech.major.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderDetailRepository detailRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }//findAll

    @Override
    public void updateOrder(Order Order) {
        orderRepository.save(Order);
    }//add or update (tuy vao pri-key)

    @Override
    public void removeOrderById(long id) {
        orderRepository.deleteById(id);
    }//delete dua vao pri-key

    @Override
    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }//search theo id

    @Override
    public boolean saveBill(OrderDTO dto) {
        User currentUser = userRepository.findById(dto.getUserId()).orElse(null);
        if (currentUser == null) {
            return false;
        }
        //order: dto > entity
        Order entity = Order.builder()
                .city(dto.getCity())
                .countryCode(dto.getCountryCode())
                .email(dto.getEmail())
                .line1(dto.getLine1())
                .line2(dto.getLine2())
                .note(dto.getNote())
                .phone(dto.getPhone())
                .postalCode(dto.getPostalCode())
                .state(dto.getState())
                .recipientName(dto.getRecipientName())
                .total(dto.getTotal())
                .user(currentUser)
                .build();
        Order order = orderRepository.save(entity);
        //convert list product duplicated into map
        Map<Long, List<Product>> distinctProduct = dto.getCart()
                .stream()
                .collect(
                        Collectors.groupingBy(Product::getId)
                );
        //create list detail of order
        List<OrderDetail> details = new ArrayList<>();
        try {
            distinctProduct.entrySet().forEach(item -> {
                OrderDetail detail = OrderDetail.builder()
                        .price(item.getValue().get(0).getPrice())
                        .qty(item.getValue().size())
                        .order(order)
                        .product(item.getValue().get(0))
                        .build();
                details.add(detail);
            });
        } catch (Exception e) {
            //return
            return false;
        }
        //save into db
        //
        //save order first then orderdetail.setorder()
        detailRepository.saveAll(details);
        //okey
        //clear cart
        return true;
    }
    //not done yet
}
