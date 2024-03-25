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
@Table(name ="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_date")
    private Date orderDate;
    @ManyToOne //todo: this should one to one
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
    //todo: this class should has relation with order class

}
