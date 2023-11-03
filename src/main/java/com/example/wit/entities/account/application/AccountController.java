package com.example.wit.entities.account.application;

import com.example.wit.entities.account.domain.AccountService;
import com.example.wit.entities.account.dto.AccountRequest;
import com.example.wit.entities.account.dto.AccountResponse;
import com.example.wit.templates.CrudController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController implements CrudController<AccountRequest, AccountResponse, Long> {
    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> read () {
        return new ResponseEntity<>(service.read(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> read (@PathVariable Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create (@Valid @RequestBody AccountRequest account) {
        service.create(account);
        return ResponseEntity.status(201).body("Account created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id, @RequestBody AccountRequest account) {
        service.update(id, account);
        return ResponseEntity.status(200).body("Account updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(200).body("Account deleted.");
    }
}
