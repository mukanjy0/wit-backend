package com.example.wit.entities.player.exceptions;

import lombok.AllArgsConstructor;

public class PlayerNotFoundException extends RuntimeException{
    final private String id;
    public static PlayerNotFoundException createWith(String id) {
        return new PlayerNotFoundException(id);
    }
    private PlayerNotFoundException(String id) {
        this.id = id;
    }
    @Override
    public String getMessage() {
        return "Player '" + id + "' not found";
    }
}
