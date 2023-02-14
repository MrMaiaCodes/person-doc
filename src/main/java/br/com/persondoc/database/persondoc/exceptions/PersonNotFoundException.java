package br.com.persondoc.database.persondoc.exceptions;

public class PersonNotFoundException extends Exception{

    private String message;

    public PersonNotFoundException(String message) {
        super(message);
    }
}
