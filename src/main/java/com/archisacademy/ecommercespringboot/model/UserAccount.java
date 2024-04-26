package com.archisacademy.ecommercespringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

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
    //sipariş numarası olustur orderNumber orderstatus string teslim edildi iade edildi refundamount da tutulabilir iadetarihi


    //onetone yapı olucak
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_account_product",
            joinColumns = @JoinColumn(name = "user_account_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

}
