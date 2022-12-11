package com.edu.hutech.major.dto;

import com.edu.hutech.major.model.Product;
import com.edu.hutech.major.model.enums.EOrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;

    private String email;

    private String recipientName; //huang feng

    private String line1; //street

    private String line2; //apt

    private String city; //Los Angeles

    private String countryCode; //US

    private String postalCode; //90002

    private String state; //CA

    private String phone;

    private String note; // allow null

    private EOrderStatus status = EOrderStatus.PENDING;

    private Integer userId;

    private List<Product> cart;

    private Double total;
}
