package com.archisacademy.ecommercespringboot.model;

import com.archisacademy.ecommercespringboot.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "price")
    private Double price;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "order_number")
    private String orderNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Column(name = "refund_amount")
    private Double refundAmount;
    @Column(name = "refund_date")
    private Timestamp refundDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
}
