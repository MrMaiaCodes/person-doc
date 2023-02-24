package br.com.persondoc.database.persondoc.exceptions;

public class DocumentNotFoundException extends Exception{

    private String code;

    private String message;

    public DocumentNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
