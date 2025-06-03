package com.example.authentification_test.utils;

public class PhoneValidator {
    public static boolean isValidPhoneNumber(String number, String operator){
        if(number == null || number.length()!=9 || !number.matches("\\d+")) return false;

        int prefix = Integer.parseInt(number.substring(0,3));

        return  switch (operator.toUpperCase()){
            case "MTN" ->(prefix >= 650 && prefix <=654) || (prefix >= 670 && prefix <= 679);
            case "ORANGE" -> (prefix >= 655 && prefix <= 659) ||(prefix >= 686 && prefix <= 699);
            default -> false;
        };
    }
}
