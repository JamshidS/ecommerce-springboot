package com.archisacademy.ecommercespringboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name = "discount")
    private double discount;

    @Column(name = "code")
    private String code;

    @ManyToMany(mappedBy = "promotionList")
    private List<Product> productList;
}
