package com.example.wit.exceptions;

public class ElementNotFoundException extends RuntimeException{
    final private String id;
    public static ElementNotFoundException createWith(String id) {
        return new ElementNotFoundException(id);
    }
    private ElementNotFoundException(String id) {
        this.id = id;
    }
    @Override
    public String getMessage() {
        return "Player '" + id + "' not found";
    }
}
