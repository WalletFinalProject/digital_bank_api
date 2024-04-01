package hei.school.digitalbankapi.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hei.school.digitalbankapi.DatabaseConfiguration;
import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Entity.AccountStatement;
import hei.school.digitalbankapi.Service.AccountService;
import hei.school.digitalbankapi.Service.AccountStatementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountStatementController {
    private AccountStatementService accountStatementService;

    public AccountStatementController(AccountStatementService accountStatementService) {
        this.accountStatementService = accountStatementService;
    }

    @GetMapping("/account-statement")
    public List<AccountStatement> getAllAccountStatement() throws SQLException {
        return accountStatementService.getAccountStatement();
    }

    @GetMapping("/account-statement/{id}")
    public ResponseEntity<List<AccountStatement>> getAccountStatementById(@PathVariable("id") UUID id) throws SQLException {
        List<AccountStatement> accountStatements = accountStatementService.getAccountStatementById(id);
        if (accountStatements == null || accountStatements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountStatements);
    }


    @PostMapping("/account-statement")
    public AccountStatement createAccountStatement(@RequestBody AccountStatement toSave) throws SQLException {
        return accountStatementService.createAccountStatement(toSave);
    }

    @PutMapping("/account-statement/{id}")
    public void updateAccountStatement(@PathVariable("id") UUID id, @RequestBody AccountStatement toUpdate) throws SQLException {
        accountStatementService.updateAccountStatement(id, toUpdate);
    }

    @DeleteMapping("/account-statement/{id}")
    public void deleteAccountStatement(@PathVariable("id") UUID id) throws SQLException {
        accountStatementService.deleteAccountStatement(id);
    }

    @GetMapping("/account-statement/by-id-account")
    public String accountStatement(@RequestParam UUID idAccount) throws SQLException, JsonProcessingException {
        List<AccountStatement> accountStatements = accountStatementService.getAccountStatementByAccountId(idAccount);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(accountStatements);
    }

}
