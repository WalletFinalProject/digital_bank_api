package hei.school.digitalbankapi.Service;

import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Repository.CrudOperationsAccount;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    private CrudOperationsAccount repository;

    public AccountService(CrudOperationsAccount repository) {
        this.repository = repository;
    }

    public List<Account> getAllAccount() throws SQLException {
        return repository.findAll();
    }

    public Account createAccount(Account toSave) throws SQLException {
        validateAccount(toSave);
        return repository.save(toSave);
    }

    public Account updateAccount(UUID id, Account toUpdate) throws SQLException {
        validateAccount(toUpdate);
        return repository.update(id, toUpdate);
    }

    public void deleteAccount(UUID id) throws SQLException {
        repository.delete(id);
    }

    private void validateAccount(Account account) throws SQLException {
        if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (account.getBalance() > (account.getNetMonthlySalary() / 3)) {
            throw new IllegalArgumentException("Balance cannot exceed one-third of the monthly salary");
        }
    }
    public List<Account> getAccountById(UUID id) throws SQLException {
        return repository.findById(id);
    }



}
