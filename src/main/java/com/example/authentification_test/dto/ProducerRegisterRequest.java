package com.example.authentification_test.dto;

import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;

@Data
public class ProducerRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String adress;

    //specified field for producer
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

    @ElementCollection
    private List<String> deliveryZones;

    private boolean acceptReturn;
    private boolean hasCustomersService;
    private String serviceHours;

}
