package com.archisacademy.ecommercespringboot.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name ="price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    /*@ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToMany(mappedBy = "productList")
    private List<User> userLists;*/
}
