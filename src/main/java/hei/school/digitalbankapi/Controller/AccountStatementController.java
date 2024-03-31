package hei.school.digitalbankapi.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hei.school.digitalbankapi.DatabaseConfiguration;
import hei.school.digitalbankapi.Entity.AccountStatement;
import hei.school.digitalbankapi.Service.AccountStatementService;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@RestController

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
    @PostMapping("/account-statement/by-id-account")
    public String accountStatement(@RequestParam UUID idAccount) throws SQLException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            List<AccountStatement> accountStatements = getAllAccountStatement(connection, idAccount);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(accountStatements);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching account statements", e);
        }
    }

    private List<AccountStatement> getAllAccountStatement(Connection connection, UUID idAccount) throws SQLException {
        return accountStatementService.getAccountStatement();
    }
}
