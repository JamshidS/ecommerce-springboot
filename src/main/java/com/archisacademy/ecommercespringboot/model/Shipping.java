package com.archisacademy.ecommercespringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name ="shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "shipped_at")
    private Date shippedAt;
    @ManyToMany
    @JoinTable(
            name = "shipping_product",
            joinColumns = @JoinColumn(name = "shipping_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;
    @ManyToMany
    @JoinTable(
            name = "shipping_user",
            joinColumns = @JoinColumn(name = "shipping_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userList;


}
