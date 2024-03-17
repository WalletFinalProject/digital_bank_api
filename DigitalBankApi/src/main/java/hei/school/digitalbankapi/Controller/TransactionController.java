package hei.school.digitalbankapi.Controller;


import hei.school.digitalbankapi.Entity.Transaction;
import hei.school.digitalbankapi.Repository.CrudOperationsTransaction;
import hei.school.digitalbankapi.Service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TransactionController {
    private TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/transaction")
    public List<Transaction> getAllTransaction() throws SQLException {
        return service.getAllTransaction();
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(Transaction toSave) throws SQLException{
        return  service.createTransaction(toSave);
    }

    @PutMapping("/transaction/{id}")
    public void updateTransaction(@PathVariable("id") int id, Transaction toUpdate) throws SQLException{
        service.updateTransaction(id,toUpdate);
    }

    @DeleteMapping("/transaction/{id}")
    public void deleteTransaction(@PathVariable("id") int id) throws SQLException{
        service.deleteTransaction(id);
    }

}
