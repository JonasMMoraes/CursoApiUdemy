package com.example.cursoapiudemy.util;

public class NumberValidation {

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null)
            return false;

        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0.9]+");
    }
}
