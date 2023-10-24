package com.example.wit.entities.player.exceptions;

import lombok.AllArgsConstructor;

public class PlayerNotFoundException extends RuntimeException{
    final private String username;
    public PlayerNotFoundException createWith(String username) {
        return new PlayerNotFoundException(username);
    }
    private PlayerNotFoundException(String username) {
        this.username = username;
    }
    @Override
    public String getMessage() {
        return "Player '" + username + "' not found";
    }
}
