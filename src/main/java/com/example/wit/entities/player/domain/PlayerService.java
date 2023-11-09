package com.example.wit.entities.player.domain;

import com.example.wit.entities.player.dto.PlayerRequest;
import com.example.wit.entities.player.dto.PlayerResponse;

import java.util.List;

public interface PlayerService {
    public List<PlayerResponse> read ();

    public PlayerResponse read (Long id);

    public void update (Long id, PlayerRequest player);

    public void create (PlayerRequest player);

    public void delete (Long id);
}
