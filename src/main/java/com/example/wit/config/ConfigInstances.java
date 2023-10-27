package com.example.wit.config;

import com.example.wit.entities.account.domain.Account;
import com.example.wit.entities.account.dto.AccountRequest;
import com.example.wit.entities.account.dto.AccountResponse;
import com.example.wit.entities.career.domain.CareerRepository;
import com.example.wit.entities.platform.domain.PlatformRepository;
import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerRepository;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.team.domain.TeamRepository;
import com.example.wit.entities.university.domain.UniversityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigInstances {
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Bean
    public ModelMapper instanceModelMapper() {
        ModelMapper mapper = new ModelMapper();

//        TypeMap<PlayerSignUp, Player> typeMap = mapper.createTypeMap(PlayerSignUp.class, Player.class);
//
//        PropertyMap<PlayerSignUp, Player> propertyMap = new PropertyMap<PlayerSignUp, Player>() {
//            @Override
//            protected void configure() {
//                skip(player.setId());
//            }
//        }

        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.typeMap(AccountRequest.class, Account.class).addMappings(
                mpr -> {
                    mpr.map(account -> platformRepository.findById(account.getPlatformId()), Account::setPlatform);
                    mpr.map(account -> playerRepository.findById(account.getPlayerId()), Account::setPlayer);
                }
        );

        mapper.typeMap(Account.class, AccountResponse.class).addMappings(
                mpr -> {
                    mpr.map(account -> account.getPlatform().getId(), AccountResponse::setPlatformId);
                    mpr.map(account -> account.getPlayer().getId(), AccountResponse::setPlayerId);
                }
        );

        mapper.typeMap(PlayerSignUp.class, Player.class).addMappings(
                mpr -> {
                    mpr.skip(Player::setId);
                    mpr.map(player -> universityRepository.findById(player.getUniversityId()), Player::setUniversity);
                    mpr.map(player -> careerRepository.findById(player.getCareerId()), Player::setCareer);
                    mpr.map(player -> teamRepository.findById(player.getTeamId()), Player::setTeam);
                }
        );

        mapper.typeMap(Player.class, PlayerResponse.class).addMappings(
                mpr -> {
                    mpr.map(player -> player.getUniversity().getId(), PlayerResponse::setUniversityId);
                    mpr.map(player -> player.getCareer().getId(), PlayerResponse::setCareerId);
                    mpr.map(player -> player.getTeam().getId(), PlayerResponse::setTeamId);
                }
        );
        mapper.getConfiguration().setAmbiguityIgnored(false);

        return mapper;

    }
}
