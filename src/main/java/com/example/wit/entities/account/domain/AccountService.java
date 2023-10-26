package com.example.wit.entities.account.domain;

import com.example.wit.entities.account.dto.AccountRequest;
import com.example.wit.entities.account.dto.AccountResponse;
import com.example.wit.entities.account.utils.AccountUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Service
public class AccountService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AccountRepository repository;
    TypeMap<Account, AccountResponse> propertyMapper;
    public AccountService() {
        propertyMapper = mapper.createTypeMap(Account.class, AccountResponse.class);
        propertyMapper.addMapping(Account::getPlatform, AccountResponse::setPlatformId);
        propertyMapper.addMapping(Account::getPlayer, AccountResponse::setPlayerId);
    }


    public ResponseEntity<List<AccountResponse>> read() {
        List<AccountResponse> accounts = repository.findAll().stream().map(account -> mapper.map(account, AccountResponse.class)).toList();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    public ResponseEntity<AccountResponse> read (Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isEmpty()) {
            throw ElementNotFoundException.createWith("Account", id.toString());
        }

        return new ResponseEntity<>(mapper.map(account.get(), AccountResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<String> create (AccountRequest account) {
        String handle = account.getHandle();
        Short platformId = account.getPlatformId();
        Long playerId = account.getPlayerId();

        if (repository.existsAccountByHandleAndPlatformId(handle, platformId)) {
            throw ElementAlreadyExistsException.createWith(handle + "-" + platformId.toString(), "(handle, platform id)");
        }
        if (repository.existsAccountByPlayerIdAndPlatformId(playerId, platformId)) {
            throw ElementAlreadyExistsException.createWith(playerId.toString() + "-" + platformId.toString(), "(player id, platform id)");
        }

        repository.save(mapper.map(account, Account.class));
        return ResponseEntity.status(201).body("Account created.");
    }

    public ResponseEntity<String> update (Long id, AccountRequest account) {
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

        if (!(handle+platformId.toString()).equals(newHandle+newPlatformId.toString())
            && repository.existsAccountByHandleAndPlatformId(newHandle, newPlatformId)) {
            throw ElementAlreadyExistsException.createWith(newHandle + "-" + newPlatformId, "(handle, platform id)");
        }

        if (!(playerId+platformId.toString()).equals(newPlayerId+newPlatformId.toString())
                && repository.existsAccountByPlayerIdAndPlatformId(newPlayerId, newPlatformId)) {
            throw ElementAlreadyExistsException.createWith(newPlayerId + "-" + newPlatformId, "(player id, platform id)");
        }

        repository.save(updated);
        return ResponseEntity.status(200).body("Account updated.");
    }

    public ResponseEntity<String> delete (Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            throw ElementNotFoundException.createWith("Account", id.toString());
        }

        repository.deleteById(id);
        return ResponseEntity.status(200).body("Account deleted.");
    }
}
