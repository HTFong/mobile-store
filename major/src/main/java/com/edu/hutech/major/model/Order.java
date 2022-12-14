package com.edu.hutech.major.model;

import com.edu.hutech.major.model.enums.EOrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Double total;



//    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10) default 'PENDING'")
    private String status;

    private String note; // allow null

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
