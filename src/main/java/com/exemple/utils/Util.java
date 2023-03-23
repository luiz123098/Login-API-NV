package com.exemple.utils;

import com.exemple.exceptions.BusinessRules;
import com.exemple.exceptions.ExceptionVO;
import com.exemple.message.Message;
import com.exemple.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Util {
    @Autowired
    public UserModel userModel;
    public String message;

    public boolean validateLogin(String login) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(login);
        // Compilar o regex e aplicá-lo à String do email
        try {
            userModel.existsByLogin(login);
            return matcher.matches();
            // Retornar se o email corresponde ao regex
        } catch (Exception e) {
            setMessage(Message.UtilLogin.USER_ALREADY_REGISTER);
            throw new BusinessRules(getMessage());
        }
    }
    public boolean validatePassword(String password) {
    try{
        if (password == null || password.isEmpty()) {

            setMessage(Message.UtilPassword.EMPTY_PASSWORD);
            return false;

        }

        if (password.length() < 8) {
            setMessage(Message.UtilPassword.TOO_SHORT);
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            setMessage(Message.UtilPassword.MISSING_DIGIT);
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            setMessage(Message.UtilPassword.MISSING_UPPERCASE);
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            setMessage(Message.UtilPassword.MISSING_LOWERCASE);
            return false;
        }

        if (!password.matches(".*[@#$%^&+=!].*")) {
            setMessage(Message.UtilPassword.MISSING_SPECIAL);
            return false;
        }
        return true;
    }catch (Exception e){
        throw new BusinessRules(getMessage());
    }
        }

        public boolean validateCpf(String cpf){
            //Metodo criado por ChatGpt, coloquei as anotações
            try {
                //Verifica se a senha não é nula
                if (cpf == null || cpf.trim().isEmpty()) {
                    setMessage(Message.UtilCPF.EMPTY_CPF);
                    return false;
                }

                // Remove pontos e traços do CPF
                cpf = cpf.replaceAll("\\D", "");

                // Verifica se o CPF tem 11 dígitos
                if (cpf.length() != 11) {
                    setMessage(Message.UtilCPF.DIGITS_COUNT);
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
                    setMessage(Message.UtilCPF.EQUALS_DIGITS);
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
                        setMessage(Message.UtilCPF.INVALID_CPF);
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                throw new BusinessRules(getMessage());
            }
        }

        public void setMessage (String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

}
