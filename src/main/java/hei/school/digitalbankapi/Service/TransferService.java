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

    public void transferMoney(UUID idAccountFrom, UUID idAccountTo, Double amount, String transferReason) throws Exception {

        List<Account> accountsFrom = accountRepository.findById(idAccountFrom);
        List<Account> accountsTo = accountRepository.findById(idAccountTo);

        if (accountsFrom.isEmpty() || accountsTo.isEmpty()) {
            throw new Exception("Account not found.");
        }

        Account fromAccount = accountsFrom.get(0);
        Account toAccount = accountsTo.get(0);

        if (!fromAccount.isAuthorizeCredits() || !toAccount.isAuthorizeCredits()) {
            throw new Exception("Account not authorized to receive credits.");
        }

        if (fromAccount.getBalance() < amount) {
            throw new Exception("Insufficient balance in the source account.");
        }


        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);


        accountRepository.update(idAccountFrom, fromAccount);
        accountRepository.update(idAccountTo, toAccount);


        Transfer transfer = new Transfer();
        transfer.setAccountIdRecipient(idAccountTo);
        transfer.setAmount(amount);
        transfer.setTransferReason(transferReason);
        transfer.setEffectiveDate(new java.sql.Timestamp(System.currentTimeMillis()));
        transfer.setRegistrationDate(new java.sql.Timestamp(System.currentTimeMillis()));
        transfer.setStatus("Done");

        repository.save(transfer);
    }



}
