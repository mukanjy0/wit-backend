package com.example.wit.entities.account.application;

import com.example.wit.entities.account.domain.AccountService;
import com.example.wit.entities.account.dto.AccountRequest;
import com.example.wit.entities.account.dto.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> read () {
        return service.read();
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> read (@PathVariable Long id) {
        return service.read(id);
    }
    @PostMapping
    public ResponseEntity<String> create (@RequestBody AccountRequest account) {
        return service.create(account);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, AccountRequest account) {
        return service.update(id, account);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        return service.delete(id);
    }
}
