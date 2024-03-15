package hei.school.digitalbankapi.Repository;

import hei.school.digitalbankapi.Entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public class CrudOperationsAccount implements CrudOperations<Account>{
    private CrudOperationsAccount connection;

    public CrudOperationsAccount(CrudOperationsAccount connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() throws SQLException {
        return null;
    }

    @Override
    public Account save(Account toSave) throws SQLException {
        return null;
    }

    @Override
    public Account update(Account toUpdate) throws SQLException {
        return null;
    }

    @Override
    public void delete(int toDelete) throws SQLException {

    }
}
