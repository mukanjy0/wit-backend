package com.example.wit.entities.player.domain;

import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.career.domain.CareerRepository;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerRequest;
import com.example.wit.entities.team.domain.Team;
import com.example.wit.entities.team.domain.TeamRepository;
import com.example.wit.entities.university.domain.University;
import com.example.wit.entities.university.domain.UniversityRepository;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import com.example.wit.entities.player.utils.PlayerUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserPlayerService implements PlayerService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PlayerRepository repository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private UniversityRepository universityRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<Player> player = repository.findPlayerByUsername(username);
                if (player.isEmpty()) {
                    throw new UsernameNotFoundException("Player with username '" + username + "' not found");
                }
                return player.get();
            }
        };
    }

    public List<PlayerResponse> read () {
        return repository.findAll().stream().map(player -> mapper.map(player, PlayerResponse.class)).toList();
    }

    public PlayerResponse read (Long id) {
        Optional<Player>  player = repository.findById(id);
        if (player.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }

        return mapper.map(player.get(), PlayerResponse.class);
    }

    public void update (Long id, PlayerRequest player) {
        Optional<Player> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }

        Player previous = original.get();
        String username = previous.getUsername();

        Player updated = PlayerUtils.updatePlayer(previous, player);
        String newUsername = updated.getUsername();

        if (!username.equals(newUsername) && repository.existsPlayerByUsername(newUsername)) {
            throw ElementAlreadyExistsException.createWith(newUsername, "username");
        }

        var teamId = player.getTeamId();
        if (teamId != null) {
            Optional<Team> team = teamRepository.findById(teamId);
            if (team.isEmpty()) {
                throw ElementNotFoundException.createWith("Team", teamId.toString());
            }
            updated.setTeam(team.get());
        }

        var careerId = player.getCareerId();
        if (careerId != null) {
            Optional<Career> career = careerRepository.findById(careerId);
            if (career.isEmpty()) {
                throw ElementNotFoundException.createWith("Career", careerId.toString());
            }
            updated.setCareer(career.get());
        }

        var universityId = player.getUniversityId();
        if (universityId != null) {
            Optional<University> university = universityRepository.findById(universityId);
            if (university.isEmpty()) {
                throw ElementNotFoundException.createWith("University", universityId.toString());
            }
            updated.setUniversity(university.get());
        }

        repository.save(updated);
    }

    public void create (PlayerRequest player) {
        String username = player.getUsername();
        if (repository.existsPlayerByUsername(username)) {
            throw ElementAlreadyExistsException.createWith(username, "username");
        }

        Player newPlayer = mapper.map(player, Player.class);
        var passwordEncoder = new BCryptPasswordEncoder();
        newPlayer.setPassword(passwordEncoder.encode(player.getPassword()));
        newPlayer.setBestCategory(newPlayer.getCurrentCategory());
        newPlayer.setRegistrationDate(LocalDate.now());

        repository.save(newPlayer);
    }

    public void delete (Long id) {
        Optional<Player> player = repository.findById(id);
        if (player.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }
        repository.deleteById(id);
    }
}
