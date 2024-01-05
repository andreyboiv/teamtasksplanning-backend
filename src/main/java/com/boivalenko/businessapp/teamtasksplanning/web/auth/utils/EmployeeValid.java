package com.boivalenko.businessapp.teamtasksplanning.web.auth.utils;

import com.boivalenko.businessapp.teamtasksplanning.web.auth.viewmodel.EmployeeVm;

import java.util.regex.Pattern;

public final class EmployeeValid {

    // Regex laut RFC822
    private static final String EMAIL_REGEX_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_LOGIN_LENGTH = 6;

    private EmployeeValid(){
        throw new AssertionError("Es dürfen keine Objekte des Klasses erzeugt werden");
    }

    public static String isEmployeeValid(EmployeeVm employee) {

        String error = "";
        if (employee.getId() != null) {
            error = "ID wird automatisch generiert. Man muss da nichts eingeben";
        }

        if (employee.getLogin() == null || employee.getLogin().isEmpty() || employee.getLogin().toLowerCase().contains("null")) {
            error = "LOGIN darf weder NULL noch leer sein";
        }

        if (employee.getLogin().length() < MIN_LOGIN_LENGTH) {
            error = "LOGIN muss mindestens " + MIN_LOGIN_LENGTH + " Symbole enthalten";
        }

        if (employee.getPassword() == null || employee.getPassword().isEmpty() || employee.getPassword().toLowerCase().contains("null")) {
            error = "PASSWORD darf weder NULL noch leer sein";
        }

        if (employee.getPassword().length() < MIN_PASSWORD_LENGTH) {
            error = "PASSWORD muss mindestens " + MIN_PASSWORD_LENGTH + " Symbole enthalten";
        }

        if (employee.getEmail() == null || employee.getEmail().isEmpty() || employee.getEmail().toLowerCase().contains("null")) {
            error = "EMAIL darf weder NULL noch leer sein";
        }

        //E-mail Validation
        if (!isValidEmailAddress(employee.getEmail())) {
            error = "EMAIL Format ist unkorrekt";
        }

        return error;
    }

    private static boolean isValidEmailAddress(String email) {
        return patternMatches(email, EMAIL_REGEX_PATTERN);
    }

    private static boolean patternMatches(String email, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public static String isEmployeeValidWithOutEmail(EmployeeVm employeeVm) {
        String error = "";
        if (employeeVm.getId() != null) {
            error = "ID wird automatisch generiert. Man muss da nichts eingeben";
        }

        if (employeeVm.getLogin() == null || employeeVm.getLogin().isEmpty() || employeeVm.getLogin().toLowerCase().contains("null")) {
            error = "LOGIN darf weder NULL noch leer sein";
        }

        if (employeeVm.getPassword() == null || employeeVm.getPassword().isEmpty() || employeeVm.getPassword().toLowerCase().contains("null")) {
            error = "PASSWORD darf weder NULL noch leer sein";
        }

        return error;
    }
}
