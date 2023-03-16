package Exceptions;

public class BusinessRules extends RuntimeException{
        public BusinessRules(String msg) {
            super(msg);
        }
}
