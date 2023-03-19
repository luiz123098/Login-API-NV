package com.exemple.Utils.UtilsInterface;

public enum PasswordValidationError {
    EMPTY_MESSAGE("A senha deve ser preenchida"),
    TOO_SHORT("A senha deve ter pelo menos 8 caracteres."),
    MISSING_DIGIT("A senha deve conter pelo menos um dígito."),
    MISSING_UPPERCASE("A senha deve conter pelo menos uma letra maiúscula."),
    MISSING_LOWERCASE("A senha deve conter pelo menos uma letra minúscula."),
    MISSING_SPECIAL("A senha deve conter pelo menos um caractere especial (@#$%^&+=).");

    private String message;

    PasswordValidationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

