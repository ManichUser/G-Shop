package com.example.authentification_test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //infos entreprise
    private String companyName;
    private String registrationNumber;
    private String companyAdress;
    private String companyEmail;

    //Responsable
    private String managerName;
    private String managerPhone;
    private String managerEmail;
    private String identityType;
    private String identityNumber;

    //bank info
    private boolean hasMTN;
    private String mtnNumber;

    private boolean hasORANGE;
    private String orangeNumber;

    //products
    private String productCategories;
    private String productDescription;
    private String averageStock;
    private List<String> deliveryZones;

    private boolean acceptReturn;
    private boolean hasCustomersService;
    private String serviceHours;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
