package com.example.wit.entities.player.domain;

import com.example.wit.entities.career.domain.CareerRepository;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.player.dto.PlayerUpdate;
import com.example.wit.entities.team.domain.TeamRepository;
import com.example.wit.entities.university.domain.UniversityRepository;
import com.example.wit.exceptions.ElementNotFoundException;
import com.example.wit.entities.player.utils.PlayerUtils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
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
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private TeamRepository teamRepository;
    TypeMap<PlayerSignUp, Player> typeMapRequest;
    TypeMap<Player, PlayerResponse> typeMapResponse;

    @PostConstruct
    private void configureMapper() {
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        mapper.getConfiguration().setAmbiguityIgnored(true);
        typeMapRequest = mapper.createTypeMap(PlayerSignUp.class, Player.class);
        mapper.getConfiguration().setAmbiguityIgnored(false);

        typeMapRequest.addMappings(mapper -> mapper.skip(Player::setId));
        typeMapRequest.addMappings(
                mapper -> mapper.map(player -> universityRepository.findById(player.getUniversityId()), Player::setUniversity)
        );
        typeMapRequest.addMappings(
                mapper -> mapper.map(player -> careerRepository.findById(player.getCareerId()), Player::setCareer)
        );
        typeMapRequest.addMappings(
                mapper -> mapper.map(player -> teamRepository.findById(player.getTeamId()), Player::setTeam)
        );

        typeMapResponse = mapper.createTypeMap(Player.class, PlayerResponse.class);
        typeMapResponse.addMappings(
                mapper -> mapper.map(player -> player.getUniversity().getId(), PlayerResponse::setUniversityId)
        );
        typeMapResponse.addMappings(
                mapper -> mapper.map(player -> player.getCareer().getId(), PlayerResponse::setCareerId)
        );
        typeMapResponse.addMappings(
                mapper -> mapper.map(player -> player.getTeam().getId(), PlayerResponse::setTeamId)
        );
    }

    public ResponseEntity<List<Player>> read () {
        List<Player> players = repository.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    public ResponseEntity<Player> read (Long id) throws ElementNotFoundException {
        Optional<Player>  player = repository.findById(id);
        if (player.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }

        return new ResponseEntity<>(player.get(), HttpStatus.OK);
    }
    public ResponseEntity<String> create (PlayerSignUp player) {
        repository.save(mapper.map(player, Player.class));
        return ResponseEntity.status(201).body("Player created.");
    }

    public ResponseEntity<String> update (Long id, PlayerUpdate p) throws ElementNotFoundException, IllegalArgumentException {
        Optional<Player> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
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

    public ResponseEntity<String> delete (Long id) throws ElementNotFoundException {
        Optional<Player> player = repository.findById(id);
        if (player.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }
        repository.deleteById(id);
        return ResponseEntity.status(200).body("Player with id " + id.toString() + " deleted.");
    }
}
