package hei.school.digitalbankapi.Service;


import hei.school.digitalbankapi.Entity.Transfer;
import hei.school.digitalbankapi.Repository.CrudOperationsTransfer;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TransferService {
    private CrudOperationsTransfer repository;

    public TransferService(CrudOperationsTransfer repository) {
        this.repository = repository;
    }

    public List<Transfer> getTransfer() throws SQLException {
        return repository.findAll();
    }

    public Transfer createTransfer(Transfer toSave) throws SQLException{
        return repository.save(toSave);
    }

    public void updateAccount(int id, Transfer toUpdate) throws SQLException{
         repository.update(id,toUpdate);
    }

    public void deleteAccount(int id) throws  SQLException{
        repository.delete(id);
    }
}
