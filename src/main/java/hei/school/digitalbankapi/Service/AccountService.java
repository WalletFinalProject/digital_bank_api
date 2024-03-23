package hei.school.digitalbankapi.Service;


import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Repository.CrudOperationsAccount;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccountService {
    private CrudOperationsAccount repository;

    public AccountService(CrudOperationsAccount repository) {
        this.repository = repository;
    }

    public List<Account> getAllAccount() throws SQLException {
        return repository.findAll();
    }

    public Account createAccount(Account toSave) throws SQLException{
        return  repository.save(toSave);
    }

    public Account updateAccount(int id, Account toUpdate) throws SQLException{
         return repository.update(id,toUpdate);
    }

    public void deleteAccount(int id) throws SQLException{
         repository.delete(id);
    }
}
