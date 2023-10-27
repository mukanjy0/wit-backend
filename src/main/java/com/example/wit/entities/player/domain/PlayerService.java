package com.example.wit.entities.player.domain;

import com.example.wit.auth.domain.JwtService;
import com.example.wit.auth.dto.JwtAuthenticationResponse;
import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.career.domain.CareerRepository;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerSignIn;
import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.player.dto.PlayerUpdate;
import com.example.wit.entities.team.domain.Team;
import com.example.wit.entities.team.domain.TeamRepository;
import com.example.wit.entities.university.domain.University;
import com.example.wit.entities.university.domain.UniversityRepository;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import com.example.wit.entities.player.utils.PlayerUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
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

    public PlayerResponse read (Long id) throws ElementNotFoundException {
        Optional<Player>  player = repository.findById(id);
        if (player.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }

        return mapper.map(player.get(), PlayerResponse.class);
    }

    public void update (Long id, PlayerUpdate player) {
        Optional<Player> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }

        Optional<Team> team = teamRepository.findById(player.getTeamId());
        if (team.isEmpty()) {
            throw ElementNotFoundException.createWith("Team", player.getTeamId().toString());
        }

        Optional<Career> career = careerRepository.findById(player.getCareerId());
        if (career.isEmpty()) {
            throw ElementNotFoundException.createWith("Career", player.getCareerId().toString());
        }

        Optional<University> university = universityRepository.findById(player.getUniversityId());
        if (university.isEmpty()) {
            throw ElementNotFoundException.createWith("University", player.getUniversityId().toString());
        }

        Player previous = original.get();
        String username = previous.getUsername();

        Player updated = PlayerUtils.updatePlayer(previous, player);
        String newUsername = updated.getUsername();

        if (!username.equals(newUsername) && repository.existsPlayerByUsername(newUsername)) {
            throw ElementAlreadyExistsException.createWith(newUsername, "username");
        }

        updated.setTeam(team.get());
        updated.setCareer(career.get());
        updated.setUniversity(university.get());

        repository.save(updated);
    }

    public void delete (Long id) {
        Optional<Player> player = repository.findById(id);
        if (player.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", id.toString());
        }
        repository.deleteById(id);
    }
}
