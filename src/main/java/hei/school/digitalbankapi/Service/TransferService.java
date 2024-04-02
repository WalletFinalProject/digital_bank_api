package hei.school.digitalbankapi.Service;


import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Entity.Transfer;
import hei.school.digitalbankapi.Repository.CrudOperationsAccount;
import hei.school.digitalbankapi.Repository.CrudOperationsTransfer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransferService {
    private CrudOperationsTransfer repository;
    private CrudOperationsAccount accountRepository;


    public List<Transfer> getTransfer() throws SQLException {
        return repository.findAll();
    }

    public Transfer createTransfer(Transfer toSave) throws SQLException{
        return repository.save(toSave);
    }

    public Transfer updateAccount(UUID id, Transfer toUpdate) throws SQLException{
         return repository.update(id,toUpdate);
    }

    public void deleteAccount(UUID id) throws  SQLException{
        repository.delete(id);
    }

    public List<Transfer> getTransferById(UUID id) throws SQLException {
        return repository.findById(id);
    }


}
