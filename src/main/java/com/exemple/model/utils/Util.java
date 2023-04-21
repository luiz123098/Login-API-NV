package com.exemple.model.utils;

import com.exemple.model.exceptions.BusinessRules;
import com.exemple.model.message.Message;
import com.exemple.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Util {
    @Autowired
    public UserRepository userRepository;
    public String message;

    public boolean validateLogin(String login) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(login);
        // Compilar o regex e aplicá-lo à String do email
        try {
            if (!userRepository.existsByLogin(login)){
                return matcher.matches();
            }else {
                setMessage(Message.UtilLogin.USER_ALREADY_REGISTER);
                return false;
            }

            // Retornar se o email corresponde ao regex
        } catch (Exception e) {
            setMessage(Message.UtilLogin.USER_ALREADY_REGISTER);
            e.getMessage();
            return false;
        }
    }
    public boolean validatePassword(String password) {
        Boolean returns = Boolean.TRUE;
    try{
        if (password == null || password.isEmpty()) {

            setMessage(Message.UtilPassword.EMPTY_PASSWORD);
            returns = false;
            throw new BusinessRules(getMessage());

        }

        if (password.length() < 8) {
            setMessage(Message.UtilPassword.TOO_SHORT);
            returns = false;
            throw new BusinessRules(getMessage());
        }

        if (!password.matches(".*\\d.*")) {
            setMessage(Message.UtilPassword.MISSING_DIGIT);
            returns = false;
            throw new BusinessRules(getMessage());
        }

        if (!password.matches(".*[A-Z].*")) {
            setMessage(Message.UtilPassword.MISSING_UPPERCASE);
            returns = false;
            throw new BusinessRules(getMessage());
        }

        if (!password.matches(".*[a-z].*")) {
            setMessage(Message.UtilPassword.MISSING_LOWERCASE);
            returns = false;
            throw new BusinessRules(getMessage());
        }

        if (!password.matches(".*[@#$%^&+=!].*")) {
            setMessage(Message.UtilPassword.MISSING_SPECIAL);
            returns = false;
            throw new BusinessRules(getMessage());
        }
        return returns;
    }catch (Exception e){
        e.getMessage();
        return false;
    }
        }

        public boolean validateCpf(String cpf){
            //Metodo criado por ChatGpt, coloquei as anotações
            try {
                boolean returns = Boolean.TRUE;
                //Verifica se a senha não é nula
                if (cpf == null || cpf.trim().isEmpty()) {
                    setMessage(Message.UtilCPF.EMPTY_CPF);
                    returns = false;
                    throw new BusinessRules(getMessage());
                }

                // Remove pontos e traços do CPF
                cpf = cpf.replaceAll("\\D", "");

                // Verifica se o CPF tem 11 dígitos
                if (cpf.length() != 11) {
                    setMessage(Message.UtilCPF.DIGITS_COUNT);
                    returns = false;
                    throw new BusinessRules(getMessage());
                }

                // Verifica se todos os dígitos do CPF são iguais
                boolean allDigitsEqual = true;
                for (int i = 1; i < cpf.length(); i++) {
                    if (cpf.charAt(i) != cpf.charAt(0)) {
                        allDigitsEqual = false;
                    }
                }
                if (allDigitsEqual) {
                    setMessage(Message.UtilCPF.EQUALS_DIGITS);
                    returns = false;
                    throw new BusinessRules(getMessage());
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
                //ESTAVA DANDO ERRO ENTÃO COMMITEI PARA RESOLVER DEPOIS ESSA QUESTÃO
                //int remainder = sum % 11;
                //int firstVerifier = (remainder < 2) ? 0 : (11 - remainder);
                //digits[9] = firstVerifier;
                //sum = 0;
                //for (int i = 0; i < 10; i++) {
                //    sum += digits[i] * factors[i];
                //}
                //remainder = sum % 11;
                //int secondVerifier = (remainder < 2) ? 0 : (11 - remainder);
                //digits[10] = secondVerifier;
//
                //// Verifica se os dígitos verificadores calculados são iguais aos dígitos informados no CPF
                //for (int i = 0; i < 11; i++) {
                //    if (digits[i] != Character.getNumericValue(cpf.charAt(i))) {
                //        setMessage(Message.UtilCPF.INVALID_CPF);
                //        returns = false;
                //        throw new BusinessRules(getMessage());
                //    }
                //}
                return returns;
            } catch (Exception e) {
                throw new BusinessRules(getMessage());
            }
        }

        public boolean validateUser(String user){
            try {
                if(userRepository.existsByUser(user)){
                    return true;
                }else
                    setMessage(Message.UtilUser.NOT_USER_FOUND);
                    return false;
            }catch (Exception e){
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
