package com.example.wit.exceptions;

public class ElementAlreadyExistsException extends RuntimeException {
    private String id;
    private String idName;
    public static ElementAlreadyExistsException createWith(String id, String idName) {
        return new ElementAlreadyExistsException(id, idName);
    }
    private ElementAlreadyExistsException(String id, String idName) {
        this.id = id;
        this.idName = idName;
    }
    @Override
    public String getMessage() { return "Element with " + idName + " '" + id + "' already exists. Thus, it cannot be created.";}
}
