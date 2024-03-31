package hei.school.digitalbankapi.Controller;

import hei.school.digitalbankapi.Entity.AccountStatement;
import hei.school.digitalbankapi.Service.AccountStatementService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AccountStatementController {
    private AccountStatementService accountStatementService;

    public AccountStatementController(AccountStatementService accountStatementService) {
        this.accountStatementService = accountStatementService;
    }
    public static AccountStatementController createAccountStatement(AccountStatementService accountStatementService) {
        return new AccountStatementController(accountStatementService);
    }
    @GetMapping("/account-statement")
    public List<AccountStatement> getAllAccountStatement() throws SQLException {
        return accountStatementService.getAccountStatement();
    }

    @PostMapping("/account-statement")
    public AccountStatement createAccountStatement(@RequestBody AccountStatement toSave) throws SQLException{
        return  accountStatementService.createAccountStatement(toSave);
    }

    @PutMapping("/account-statement/{id}")
    public void updateAccountStatement(@PathVariable("id") UUID id, @RequestBody AccountStatement toUpdate) throws SQLException{
        accountStatementService.updateAccountStatement(id,toUpdate);
    }

    @DeleteMapping("/account-statement/{id}")
    public void deleteAccountStatement(@PathVariable("id") UUID id) throws SQLException{
        accountStatementService.deleteAccountStatement(id);
    }
}
