package com.exemple.model.exceptions;

public class BusinessRules extends RuntimeException{
        public BusinessRules(String msg) {
            super(msg);
        }
}
