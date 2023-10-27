package com.example.wit.config;

import com.example.wit.entities.account.domain.Account;
import com.example.wit.entities.account.dto.AccountRequest;
import com.example.wit.entities.account.dto.AccountResponse;
import com.example.wit.entities.career.domain.CareerRepository;
import com.example.wit.entities.platform.domain.PlatformRepository;
import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerRepository;
import com.example.wit.entities.player.domain.category.Category;
import com.example.wit.entities.player.domain.category.CategoryConverter;
import com.example.wit.entities.player.domain.role.Role;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.team.domain.TeamRepository;
import com.example.wit.entities.university.domain.UniversityRepository;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Configuration
public class ConfigInstances {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @Bean
    public ModelMapper instanceModelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setSkipNullEnabled(true);

        mapper.getConfiguration().setAmbiguityIgnored(true);

        mapper.typeMap(AccountRequest.class, Account.class).addMappings(
                mpr -> {
                    mpr.skip(Account::setId);
                    mpr.using(ctx -> platformRepository.findById((Short) ctx.getSource()).get())
                            .map(AccountRequest::getPlatformId, Account::setPlatform);
                    mpr.using(ctx -> playerRepository.findById((Long) ctx.getSource()).get())
                            .map(AccountRequest::getPlayerId, Account::setPlayer);
                }
        );

        mapper.typeMap(Account.class, AccountResponse.class).addMappings(
                mpr -> {
                    mpr.map(account -> account.getPlatform().getId(), AccountResponse::setPlatformId);
                    mpr.map(account -> account.getPlayer().getId(), AccountResponse::setPlayerId);
                }
        );

        mapper.getConfiguration().setAmbiguityIgnored(false);

        return mapper;
    }
}
