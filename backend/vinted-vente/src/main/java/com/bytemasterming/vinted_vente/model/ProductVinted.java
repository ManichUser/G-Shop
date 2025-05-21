package com.bytemasterming.vinted_vente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.bytemasterming.vinted_vente.model.ProductStatus;
import com.bytemasterming.vinted_vente.model.Offer;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productVinted")
public class ProductVinted {

    @Id
    private String id;

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
