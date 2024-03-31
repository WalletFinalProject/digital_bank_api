package hei.school.digitalbankapi.Controller;


import hei.school.digitalbankapi.Entity.Transaction;
import hei.school.digitalbankapi.Repository.CrudOperationsTransaction;
import hei.school.digitalbankapi.Service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/transaction/{id}")
    public List<Transaction> getById(@PathVariable("id") UUID id) throws SQLException{
        return service.getById(id);
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Transaction toSave) throws SQLException{
        return  service.createTransaction(toSave);
    }

    @PutMapping("/transaction/{id}")
    public void updateTransaction(@PathVariable("id") UUID id, @RequestBody Transaction toUpdate) throws SQLException{
        service.updateTransaction(id,toUpdate);
    }

    @DeleteMapping("/transaction/{id}")
    public void deleteTransaction(@PathVariable("id") UUID id) throws SQLException{
        service.deleteTransaction(id);
    }

}
