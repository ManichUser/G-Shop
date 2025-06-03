package com.bytemasterming.vinted_vente.model;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String fileName;
    public String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductVinted product;

    // Constructeurs
    public Image() {}

    public Image(String fileName, String imageUrl, ProductVinted product) {
        this.fileName = fileName;
        this.imageUrl = imageUrl;
        this.product = product;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public ProductVinted getProduct() { return product; }
    public void setProduct(ProductVinted product) { this.product = product; }
}
