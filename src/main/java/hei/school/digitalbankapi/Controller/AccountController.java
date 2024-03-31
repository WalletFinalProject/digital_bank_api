package hei.school.digitalbankapi.Controller;

import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountController {
    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/account")
    public List<Account> getAllAccount() throws SQLException {
        return service.getAllAccount();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/account")
    public Account createAccount(@RequestBody Account toSave) throws SQLException {
            return service.createAccount(toSave);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/account/{id}")
    public void updateAccount(@PathVariable("id") UUID id, @RequestBody Account toUpdate) throws SQLException {
        try {
            service.updateAccount(id, toUpdate);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable("id") UUID id) throws SQLException {
        service.deleteAccount(id);
    }
}
