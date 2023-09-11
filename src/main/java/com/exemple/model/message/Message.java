package com.exemple.model.message;

public interface Message {
    interface UtilUser{
        String NOT_USER_FOUND = "Não encontramos nenhum usuario";
    }

    interface UtilRegister{
        String FAIL_REGISTER = "Falha ao cadastrar o usuario";
    }
    //Login messages
    interface UtilLogin {
        String LOGIN_SUCCESS ="Login registrado com sucesso";
        String USER_ALREADY_REGISTER ="Já existe um usuário cadastrado com esse login";
    }
    //Password error messages
    interface UtilPassword {
        String EMPTY_PASSWORD = "A senha deve ser preenchida"  ;
        String TOO_SHORT = "A senha deve ter pelo menos 8 caracteres."  ;
        String MISSING_DIGIT = "A senha deve conter pelo menos um dígito."  ;
        String MISSING_UPPERCASE = "A senha deve conter pelo menos uma letra maiúscula."  ;
        String MISSING_LOWERCASE = "A senha deve conter pelo menos uma letra minúscula."  ;
        String MISSING_SPECIAL = "A senha deve conter pelo menos um caractere especial (@#$%^&+=)."  ;
    }
    //CPF error messages
    interface UtilCPF{
        String EMPTY_CPF = "O CPF deve ser preenchido";
        String DIGITS_COUNT = "O CPF deve conter 11 digitos";
        String EQUALS_DIGITS = "O CPF não pode conter todos os digitos iguais";
        String INVALID_CPF = "CPF invalido!";
    }

    interface UtilDelete{
        String DELETE_COMPLETE = "Usuario deletado com sucesso!";
        String DELETE_FAIL = "Ocorreu um erro ao deletar o usuario!";
    }
}
