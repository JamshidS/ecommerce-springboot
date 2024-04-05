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
    @Column(name = "senderuuids")
    private String senderuuid;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
