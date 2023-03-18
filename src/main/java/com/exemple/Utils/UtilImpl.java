package com.exemple.Utils;

import com.exemple.Utils.UtilsInterface.Util;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class UtilImpl implements Util {
    @Override
    public boolean validateLogin(String login){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(login);
        // Compilar o regex e aplicá-lo à String do email

        return matcher.matches();
        // Retornar se o email corresponde ao regex
    }
}
