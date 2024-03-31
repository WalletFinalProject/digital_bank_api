package hei.school.digitalbankapi.Service;


import hei.school.digitalbankapi.Entity.Transaction;
import hei.school.digitalbankapi.Repository.CrudOperationsTransaction;
import hei.school.digitalbankapi.Repository.TransactionInformations;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private CrudOperationsTransaction repository;
    private TransactionInformations informations;

    public TransactionService(CrudOperationsTransaction repository, TransactionInformations informations) {
        this.repository = repository;
        this.informations = informations;
    }

    public List<Transaction> getAllTransaction() throws SQLException {
        return  repository.findAll();
    }

    public List<Transaction> getById(UUID id) throws SQLException{
        return  informations.findByid(id);
    }
    public Transaction createTransaction(Transaction toSave) throws  SQLException{
        return repository.save(toSave);
    }

    public Transaction updateTransaction(UUID id, Transaction toUpdate) throws  SQLException{
        return  repository.update(id,toUpdate);
    }

    public void deleteTransaction(UUID id) throws  SQLException{
        repository.delete(id);
    }
}
