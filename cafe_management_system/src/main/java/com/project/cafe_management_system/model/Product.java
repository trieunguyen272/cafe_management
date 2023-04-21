package com.project.cafe_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", unique = true)
    private String productName;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

}
