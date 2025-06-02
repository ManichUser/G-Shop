package com.bytemasterming.vinted_vente.dto;

public class ProductVintedDTO {
    private Long id;

    private String idUser;
    private String productName;
    private ProductStatus status = ProductStatus.DISPONIBLE;
    private String description;
    private String imageUrl;
    private double price;
    private String category;
    private Date dateCreation;
    private List<Offer> offres = new ArrayList<>();
}
