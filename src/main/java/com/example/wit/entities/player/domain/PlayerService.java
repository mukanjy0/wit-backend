package com.example.wit.entities.player.domain;

import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.player.dto.PlayerUpdate;
import com.example.wit.entities.player.exceptions.PlayerNotFoundException;
import com.example.wit.entities.player.utils.PlayerUtils;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PlayerRepository repository;

    public ResponseEntity<List<Player>> read () {
        List<Player> players = repository.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    public ResponseEntity<Player> read (Long id) throws PlayerNotFoundException {
        Optional<Player>  player = repository.findById(id);
        if (player.isEmpty()) {
            throw PlayerNotFoundException.createWith(id.toString());
        }

        return new ResponseEntity<>(player.get(), HttpStatus.OK);
    }
    public ResponseEntity<String> create (PlayerSignUp player) {
        repository.save(mapper.map(player, Player.class));
        return ResponseEntity.status(201).body("Player created.");
    }

    public ResponseEntity<String> update (Long id, PlayerUpdate p) throws PlayerNotFoundException, IllegalArgumentException {
        Optional<Player> original = repository.findById(id);
        if (original.isEmpty()) {
            throw PlayerNotFoundException.createWith(id.toString());
        }
        Player player = original.get();
        try {
            repository.save(PlayerUtils.updatePlayer(player, p));
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
        return ResponseEntity.status(200).body("Player with id " + id.toString() + " updated.");
    }

    public ResponseEntity<String> delete (Long id) throws PlayerNotFoundException {
        Optional<Player> player = repository.findById(id);
        if (player.isEmpty()) {
            throw PlayerNotFoundException.createWith(id.toString());
        }
        repository.deleteById(id);
        return ResponseEntity.status(200).body("Player with id " + id.toString() + " deleted.");
    }
}
