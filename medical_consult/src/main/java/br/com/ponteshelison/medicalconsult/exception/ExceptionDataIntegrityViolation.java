package br.com.ponteshelison.medicalconsult.exception;

public class ExceptionDataIntegrityViolation extends RuntimeException{
    public ExceptionDataIntegrityViolation(String message){
        super(message);
    }
}
