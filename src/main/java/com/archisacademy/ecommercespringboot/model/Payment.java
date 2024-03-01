package com.archisacademy.ecommercespringboot.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "security_code")
    private String cvc;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
  
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
