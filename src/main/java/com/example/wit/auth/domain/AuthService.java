package com.example.wit.auth.domain;

import com.example.wit.auth.dto.JwtAuthenticationResponse;
import com.example.wit.entities.career.domain.CareerRepository;
import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerRepository;
import com.example.wit.entities.player.domain.category.Category;
import com.example.wit.entities.player.domain.role.Role;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerSignIn;
import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.team.domain.TeamRepository;
import com.example.wit.entities.university.domain.UniversityRepository;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AuthService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PlayerRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private UniversityRepository universityRepository;

    public JwtAuthenticationResponse signUp (PlayerSignUp player) {
        String username = player.getUsername();
        if (repository.existsPlayerByUsername(username)) {
            throw ElementAlreadyExistsException.createWith(username, "username");
        }

        Long teamId = player.getTeamId();
        if (teamRepository.findById(teamId).isEmpty()) {
            throw ElementNotFoundException.createWith("Team", teamId.toString());
        }
        Short careerId = player.getCareerId();
        if (careerRepository.findById(careerId).isEmpty()) {
            throw ElementNotFoundException.createWith("Career", careerId.toString());
        }
        Short universityId = player.getUniversityId();
        if (universityRepository.findById(universityId).isEmpty()) {
            throw ElementNotFoundException.createWith("University", universityId.toString());
        }
        String role = player.getRole();
        if (Stream.of(Role.values())
                .filter(rl -> rl.role().equals(role))
                .toList()
                .isEmpty()) {
            throw ElementNotFoundException.createWith("Role", role);
        }

        Short category = player.getCurrentCategoryId();
        if (Stream.of(Category.values())
                .filter(cat -> cat.id().equals(category))
                .toList()
                .isEmpty()) {
            throw ElementNotFoundException.createWith("Category", category.toString());
        }

        Player newPlayer = mapper.map(player, Player.class);
        newPlayer.setPassword(passwordEncoder.encode(player.getPassword()));
        newPlayer.setBestCategory(newPlayer.getCurrentCategory());
        newPlayer.setRegistrationDate(LocalDate.now());

        repository.save(newPlayer);

        String jwt = jwtService.generateToken(newPlayer);

        return  new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn (PlayerSignIn player) {
        String username = player.getUsername();
        String password = player.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Optional<Player> user = repository.findPlayerByUsername(username);
        if (user.isEmpty()) {
            throw ElementNotFoundException.createWith("Player", username);
        }

        String jwt = jwtService.generateToken(user.get());

        return new JwtAuthenticationResponse(jwt);
    }
}
