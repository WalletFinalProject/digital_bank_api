package hei.school.digitalbankapi.Controller;


import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account toSave) throws SQLException{
        return  service.createAccount(toSave);
    }

    @PutMapping("/account/{id}")
    public void updateAccount(@PathVariable("id") int id, @RequestBody Account toUpdate) throws SQLException {
            service.updateAccount(id,toUpdate);
    }

    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable("id") int id) throws SQLException{
         service.deleteAccount(id);
    }
}
