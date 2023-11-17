package com.example.wit.config;

import com.example.wit.entities.account.domain.Account;
import com.example.wit.entities.account.dto.AccountRequest;
import com.example.wit.entities.account.dto.AccountResponse;
import com.example.wit.entities.career.domain.CareerRepository;
import com.example.wit.entities.contest.domain.Contest;
import com.example.wit.entities.contest.domain.division.Division;
import com.example.wit.entities.contest.dto.ContestPlayerResponse;
import com.example.wit.entities.contest.dto.ContestRequest;
import com.example.wit.entities.contest.dto.ContestResponse;
import com.example.wit.entities.platform.domain.PlatformRepository;
import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerRepository;
import com.example.wit.entities.player.domain.category.Category;
import com.example.wit.entities.player.domain.role.Role;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerSignUp;
import com.example.wit.entities.problem.domain.Problem;
import com.example.wit.entities.problem.domain.ProblemRepository;
import com.example.wit.entities.problem.dto.ProblemRequest;
import com.example.wit.entities.submission.domain.Submission;
import com.example.wit.entities.submission.dto.SubmissionRequest;
import com.example.wit.entities.submission.dto.SubmissionResponse;
import com.example.wit.entities.tag.domain.TagRepository;
import com.example.wit.entities.team.domain.TeamRepository;
import com.example.wit.entities.university.domain.UniversityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;
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
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ProblemRepository problemRepository;

    @Bean
    public ModelMapper instanceModelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setSkipNullEnabled(true);

        mapper.getConfiguration().setAmbiguityIgnored(true);

        mapper.typeMap(PlayerSignUp.class, Player.class).addMappings(
                mpr -> {
                    mpr.skip(Player::setId);
                    mpr.skip(Player::setPassword);
                    mpr.skip(Player::setBestCategory);
                    mpr.using(ctx -> Stream.of(Category.values())
                                    .filter(cat -> cat.id().equals((Short) ctx.getSource()))
                                    .findFirst()
                                    .orElseThrow(IllegalArgumentException::new)
                                    )
                            .map(PlayerSignUp::getCurrentCategoryId, Player::setCurrentCategory);
                    mpr.using(ctx -> Stream.of(Role.values())
                                    .filter(r -> r.role().equals((String) ctx.getSource()))
                                    .findFirst()
                                    .orElseThrow(IllegalArgumentException::new)
                                    )
                            .map(PlayerSignUp::getRole, Player::setRole);
                    mpr.using(ctx -> teamRepository.findById((Long) ctx.getSource()).orElse(null))
                            .map(PlayerSignUp::getTeamId, Player::setTeam);
                    mpr.using(ctx -> careerRepository.findById((Short) ctx.getSource()).orElse(null))
                            .map(PlayerSignUp::getCareerId, Player::setCareer);
                    mpr.using(ctx -> universityRepository.findById((Short) ctx.getSource()).orElse(null))
                            .map(PlayerSignUp::getUniversityId, Player::setUniversity);
                }
        );

        mapper.typeMap(Player.class, PlayerResponse.class).addMappings(
                mpr -> {
                    mpr.using(ctx -> ((Category) ctx.getSource()).id()).map(Player::getBestCategory, PlayerResponse::setBestCategoryId);
                    mpr.using(ctx -> ((Category) ctx.getSource()).id()).map(Player::getCurrentCategory, PlayerResponse::setCurrentCategoryId);
                    mpr.map(player -> player.getTeam().getId(), PlayerResponse::setTeamId);
                    mpr.map(player -> player.getCareer().getId(), PlayerResponse::setCareerId);
                    mpr.map(player -> player.getUniversity().getId(), PlayerResponse::setUniversityId);
                }
        );

        mapper.typeMap(Player.class, ContestPlayerResponse.class).addMappings(
                mpr -> mpr.using(ctx -> ((Category) ctx.getSource()).id())
                        .map(Player::getBestCategory, ContestPlayerResponse::setCategoryId)
        );

        mapper.typeMap(AccountRequest.class, Account.class).addMappings(
                mpr -> {
                    mpr.skip(Account::setId);
                    mpr.using(ctx -> platformRepository.findById((Short) ctx.getSource()).orElse(null))
                            .map(AccountRequest::getPlatformId, Account::setPlatform);
                    mpr.using(ctx -> playerRepository.findById((Long) ctx.getSource()).orElse(null))
                            .map(AccountRequest::getPlayerId, Account::setPlayer);
                }
        );

        mapper.typeMap(Account.class, AccountResponse.class).addMappings(
                mpr -> {
                    mpr.map(account -> account.getPlatform().getId(), AccountResponse::setPlatformId);
                    mpr.map(account -> account.getPlayer().getId(), AccountResponse::setPlayerId);
                }
        );

        mapper.typeMap(ContestRequest.class, Contest.class).addMappings(
                mpr -> {
                    mpr.skip(Contest::setBets);
                    mpr.using(ctx -> playerRepository.findById((Long) ctx.getSource()).orElse(null))
                            .map(ContestRequest::getPlayerId, Contest::setPlayer);
                    mpr.using(ctx -> Stream.of(Division.values())
                                    .filter(cont -> cont.id().equals((Character) ctx.getSource()))
                                    .findFirst()
                                    .orElseThrow(IllegalArgumentException::new)
                            )
                            .map(ContestRequest::getDivision, Contest::setDivision);
                    mpr.using(ctx ->
                                    ctx.getSource() == null
                                            ? null
                                            : ((Set<Long>) ctx.getSource())
                                            .stream()
                                            .map(problemId -> problemRepository.findById(problemId).orElse(null))
                                            .collect(Collectors.toSet()))
                            .map(ContestRequest::getProblemIds, Contest::setProblems);
                }
        );

        mapper.typeMap(Contest.class, ContestResponse.class).addMappings(
                mpr -> {
                    mpr.using(ctx -> ((Division) ctx.getSource()).id()).map(Contest::getDivision, ContestResponse::setDivision);
                    mpr.using(ctx -> mapper.map((Player) ctx.getSource(), ContestPlayerResponse.class))
                                    .map(Contest::getPlayer, ContestResponse::setPlayer);
                }
        );

        mapper.typeMap(ProblemRequest.class, Problem.class).addMappings(
                mpr -> mpr.using(ctx ->
                            ctx.getSource() == null
                            ? null
                            : ((Set<Short>) ctx.getSource())
                            .stream()
                            .map(tagId -> tagRepository.findById(tagId).orElse(null))
                            .collect(Collectors.toSet()))
                        .map(ProblemRequest::getTagIds, Problem::setTags)
        );

        mapper.typeMap(SubmissionRequest.class, Submission.class).addMappings(
                mpr -> {
                    mpr.using(ctx -> problemRepository.findById((Long) ctx.getSource()).orElse(null))
                            .map(SubmissionRequest::getProblemId, Submission::setProblem);
                    mpr.using(ctx -> playerRepository.findById((Long) ctx.getSource()).orElse(null))
                            .map(SubmissionRequest::getPlayerId, Submission::setPlayer);
                }
        );

        mapper.typeMap(Submission.class, SubmissionResponse.class).addMappings(
                mpr -> {
                    mpr.map(submission -> submission.getProblem().getId(), SubmissionResponse::setProblemId);
                    mpr.map(submission -> submission.getPlayer().getId(), SubmissionResponse::setPlayerId);
                }
        );

        mapper.getConfiguration().setAmbiguityIgnored(false);

        return mapper;
    }
}
