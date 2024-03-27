package hei.school.digitalbankapi.Controller;

import hei.school.digitalbankapi.Entity.Transfer;
import hei.school.digitalbankapi.Service.TransferService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class TransferController {
    private TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @GetMapping("/transfer")
    public List<Transfer> getAllTransfer() throws SQLException{
        return  service.getTransfer();
    }

    @PostMapping("/transfer")
    public Transfer createTransfer(@RequestBody Transfer toSave) throws SQLException{
        return service.createTransfer(toSave);
    }

    @PutMapping("/tranfert/{id}")
    public void updateTransfer(@PathVariable("id") UUID id, @RequestBody Transfer toUpdate) throws SQLException{
        service.updateAccount(id,toUpdate);
    }

    @DeleteMapping("/transfer/{id}")
    public void deleteTransfer(@PathVariable("id")UUID id) throws  SQLException{
        service.deleteAccount(id);
    }
}
