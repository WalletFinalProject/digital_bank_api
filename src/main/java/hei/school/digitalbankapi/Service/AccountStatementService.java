package hei.school.digitalbankapi.Service;

import hei.school.digitalbankapi.Entity.AccountStatement;
import hei.school.digitalbankapi.Repository.CrudOperationsAccountStatement;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AccountStatementService {
    private CrudOperationsAccountStatement crudOperationsAccountStatement;

    public AccountStatementService(CrudOperationsAccountStatement crudOperationsAccountStatement) {
        this.crudOperationsAccountStatement = crudOperationsAccountStatement;
    }
    public List<AccountStatement> getAccountStatement() throws SQLException {
        return crudOperationsAccountStatement.findAll();
    }

    public AccountStatement createAccountStatement(AccountStatement toSave) throws SQLException{
        return  crudOperationsAccountStatement.save(toSave);
    }

    public AccountStatement updateAccountStatement(UUID id, AccountStatement toUpdate) throws SQLException{
        return crudOperationsAccountStatement.update(id,toUpdate);
    }

    public void deleteAccountStatement(UUID id) throws SQLException{
        crudOperationsAccountStatement.delete(id);
    }
}
