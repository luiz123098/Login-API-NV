package com.exemple.message;

public enum RegisterMessages {
//

    //Login messages
    LOGIN_SUCCESS("Login registrado com sucesso"),
    USER_ALREADY_REGISTER("Já existe um usuário cadastrado com esse login"),

    //Password error messages
    EMPTY_MESSAGE("A senha deve ser preenchida"),
    TOO_SHORT("A senha deve ter pelo menos 8 caracteres."),
    MISSING_DIGIT("A senha deve conter pelo menos um dígito."),
    MISSING_UPPERCASE("A senha deve conter pelo menos uma letra maiúscula."),
    MISSING_LOWERCASE("A senha deve conter pelo menos uma letra minúscula."),
    MISSING_SPECIAL("A senha deve conter pelo menos um caractere especial (@#$%^&+=).");

    private String message;

    RegisterMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

