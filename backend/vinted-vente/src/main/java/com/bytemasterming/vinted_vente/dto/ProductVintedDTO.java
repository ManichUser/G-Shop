package com.bytemasterming.vinted_vente.dto;

import com.bytemasterming.vinted_vente.model.Offer;
import com.bytemasterming.vinted_vente.model.ProductStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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