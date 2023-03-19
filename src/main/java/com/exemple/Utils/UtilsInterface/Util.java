package com.exemple.Utils.UtilsInterface;


import com.exemple.Exceptions.UtilException;

public interface Util {
    public boolean validateLogin(String login);
    public boolean validatePassword(String password);
    public boolean validateCpf(String cpf) throws UtilException;
}

