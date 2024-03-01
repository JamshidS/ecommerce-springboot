package com.archisacademy.ecommercespringboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "description")
    private String description;

    @Column(name ="price")
    private double price;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "productList")
    private List<User> userLists;

    @OneToMany(mappedBy = "product")
    private List<Review> reviewList;

    @ManyToMany
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Wishlist> wishlists;

  @ManyToMany
  @JoinTable(
            name = "product_promotion",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    private List<Promotion> promotionList;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "inventory_id", referencedColumnName = "id")
  private Inventory inventory;
}
