package com.example.wit.exceptions;

public class ElementNotFoundException extends RuntimeException{
    final private String entityName;
    final private String id;
    public static ElementNotFoundException createWith(String entityName, String id) {
        return new ElementNotFoundException(entityName, id);
    }
    private ElementNotFoundException(String entityName, String id) {
        this.entityName = entityName;
        this.id = id;
    }
    @Override
    public String getMessage() {
        return entityName + " '" + id + "' not found";
    }
}
