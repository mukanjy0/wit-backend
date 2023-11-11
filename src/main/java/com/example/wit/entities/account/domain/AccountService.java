package com.example.wit.entities.account.domain;

import com.example.wit.entities.account.dto.AccountRequest;
import com.example.wit.entities.account.dto.AccountResponse;
import com.example.wit.entities.account.utils.AccountUtils;
import com.example.wit.entities.platform.domain.Platform;
import com.example.wit.entities.platform.domain.PlatformRepository;
import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerRepository;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AccountRepository repository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlatformRepository platformRepository;

    public List<AccountResponse> read(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(account -> mapper.map(account, AccountResponse.class))
                .toList();
    }

    public AccountResponse read (Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isEmpty()) {
            throw ElementNotFoundException.createWith("Account", id.toString());
        }
        return mapper.map(account.get(), AccountResponse.class);
    }

    public void create (AccountRequest account) {
        String handle = account.getHandle();
        Short platformId = account.getPlatformId();
        Long playerId = account.getPlayerId();

        if (platformRepository.findById(platformId).isEmpty()) {
            throw ElementNotFoundException.createWith("Platform", platformId.toString());
        }
        if (playerRepository.findById(playerId).isEmpty()) {
            throw ElementNotFoundException.createWith("Player", playerId.toString());
        }

        if (repository.existsAccountByHandleAndPlatformId(handle, platformId)) {
            throw ElementAlreadyExistsException.createWith(handle + "-" + platformId.toString(), "(handle, platform id)");
        }
        if (repository.existsAccountByPlayerIdAndPlatformId(playerId, platformId)) {
            throw ElementAlreadyExistsException.createWith(playerId.toString() + "-" + platformId.toString(), "(player id, platform id)");
        }

        repository.save(mapper.map(account, Account.class));
    }

    public void update (Long id, AccountRequest account) {
        Optional<Account> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Account", id.toString());
        }

        Account previous = original.get();
        String handle = previous.getHandle();
        Short platformId = previous.getPlatform().getId();
        Long playerId = previous.getPlayer().getId();

        Account updated = AccountUtils.updateAccount(previous, account);
        String newHandle = updated.getHandle();
        Short newPlatformId = updated.getPlatform().getId();
        Long newPlayerId = updated.getPlayer().getId();

        if (newPlatformId != null) {
            Optional<Platform> platform = platformRepository.findById(newPlatformId);
            if (platform.isPresent()) {
                updated.setPlatform(platform.get());
            } else {
                throw ElementNotFoundException.createWith("Platform", newPlatformId.toString());
            }
            if (newHandle != null
                && !(handle+platformId.toString()).equals(newHandle+newPlatformId.toString())
                && repository.existsAccountByHandleAndPlatformId(newHandle, newPlatformId)) {
                throw ElementAlreadyExistsException.createWith(newHandle + "-" + newPlatformId, "(handle, platform id)");
            }

            if (newPlayerId != null) {
                Optional<Player> player = playerRepository.findById(newPlayerId);
                if (player.isPresent()) {
                    updated.setPlayer(player.get());
                } else {
                    throw ElementNotFoundException.createWith("Player", newPlayerId.toString());

                }
                if (!(playerId+platformId.toString()).equals(newPlayerId+newPlatformId.toString())
                        && repository.existsAccountByPlayerIdAndPlatformId(newPlayerId, newPlatformId)) {
                    throw ElementAlreadyExistsException.createWith(newPlayerId + "-" + newPlatformId, "(player id, platform id)");
                }
            }

        }

        repository.save(updated);
    }

    public void delete (Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isEmpty()) {
            throw ElementNotFoundException.createWith("Account", id.toString());
        }

        repository.deleteById(id);
    }
}
