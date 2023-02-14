package br.com.persondoc.database.persondoc.exceptions;

public class DocumentNotFoundException extends Exception{

    private String message;

    public DocumentNotFoundException(String message) {super(message);}
}
