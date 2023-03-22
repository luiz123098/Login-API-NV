package com.exemple.Utils;

import com.exemple.Exceptions.BusinessRules;
import com.exemple.Exceptions.ExceptionVO;
import com.exemple.Message.Message;
import com.exemple.Message.RegisterMessages;
import com.exemple.Utils.UtilsInterface.Util;
import com.exemple.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UtilImpl implements Util {
    @Autowired
    public UserModel userModel;
    public String message;

    @Override
    public boolean validateLogin(String login) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(login);
        // Compilar o regex e aplicá-lo à String do email
        boolean exist = userModel.existsByLogin(login);
        if (exist) {
            throw new BusinessRules(RegisterMessages.USER_ALREADY_REGISTER.getMessage());
        }

        return matcher.matches();
        // Retornar se o email corresponde ao regex
    }

    @Override
    public boolean validatePassword(String password) {

        if (password == null || password.isEmpty()) {

            globalMessage(Message.UtilPassword.EMPTY_PASSWORD, ExceptionVO.ERROR_INFO );
            return false;

        }

        if (password.length() < 8) {
            setMessage(new BusinessRules(RegisterMessages.TOO_SHORT.getMessage()).toString());
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            setMessage(new BusinessRules(RegisterMessages.MISSING_DIGIT.getMessage()).toString());
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            setMessage(new BusinessRules(RegisterMessages.MISSING_UPPERCASE.getMessage()).toString());
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            setMessage(new BusinessRules(RegisterMessages.MISSING_LOWERCASE.getMessage()).toString());
            return false;
        }

        if (!password.matches(".*[@#$%^&+=!].*")) {
            setMessage(new BusinessRules(RegisterMessages.MISSING_SPECIAL.getMessage()).toString());
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

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void globalMessage(final String severityMessage, String exceptionVO) {
                globalMessage(severityMessage, exceptionVO);
    }
}
