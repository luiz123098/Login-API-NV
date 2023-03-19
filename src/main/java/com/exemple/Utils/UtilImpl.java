package com.exemple.Utils;

import com.exemple.Utils.UtilsInterface.PasswordValidationError;
import com.exemple.Utils.UtilsInterface.Util;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class UtilImpl implements Util{
    @Override
    public boolean validateLogin(String login){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(login);
        // Compilar o regex e aplicá-lo à String do email

        return matcher.matches();
        // Retornar se o email corresponde ao regex
    }
    @Override
    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            PasswordValidationError.EMPTY_MESSAGE.getMessage();
            return false;
        }

        if (password.length() < 8) {
            PasswordValidationError.TOO_SHORT.getMessage();
            return false;
        }

        if (!password.matches(".*\\d.*")) {
           PasswordValidationError.MISSING_DIGIT.getMessage();
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            PasswordValidationError.MISSING_UPPERCASE.getMessage();
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            PasswordValidationError.MISSING_LOWERCASE.getMessage();
            return false;
        }

        if (!password.matches(".*[@#$%^&+=!].*")) {
            PasswordValidationError.MISSING_SPECIAL.getMessage();
            return false;
        }

        return true;
    }
    @Override
    public boolean validateCpf(String cpf) {
        //Metodo criado por ChatGpt, coloquei as anotações
        try {
        //Verifica se a senha não é nula
            if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        // Remove pontos e traços do CPF
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos do CPF são iguais
        boolean allDigitsEqual = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allDigitsEqual = false;
                break;
            }
        }
        if (allDigitsEqual) {
            return false;
        }

        // Calcula os dígitos verificadores do CPF
        int[] factors = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] digits = new int[11];
        for (int i = 0; i < 9; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * factors[i];
        }
        int remainder = sum % 11;
        int firstVerifier = (remainder < 2) ? 0 : (11 - remainder);
        digits[9] = firstVerifier;
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * factors[i];
        }
        remainder = sum % 11;
        int secondVerifier = (remainder < 2) ? 0 : (11 - remainder);
        digits[10] = secondVerifier;

        // Verifica se os dígitos verificadores calculados são iguais aos dígitos informados no CPF
        for (int i = 0; i < 11; i++) {
            if (digits[i] != Character.getNumericValue(cpf.charAt(i))) {
                return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error ao tentar validar o cpf");
        }
        return true;
    }


}
