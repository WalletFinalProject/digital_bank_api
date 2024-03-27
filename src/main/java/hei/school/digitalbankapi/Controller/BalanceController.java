package hei.school.digitalbankapi.Controller;

import hei.school.digitalbankapi.Entity.BalanceHistory;
import hei.school.digitalbankapi.Service.BalanceService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class BalanceController {
    private BalanceService service;

    private BalanceController(BalanceService service) {
        this.service = service;
    }

    public static BalanceController createBalanceController(BalanceService service) {
        return new BalanceController(service);
    }

    @GetMapping("/balance-history")
    public List<BalanceHistory> getAllBalanceHistory() throws SQLException {
        return service.getBalanceHistory();
    }

    @PostMapping("/balance-history")
    public BalanceHistory createBalanceHistory(@RequestBody BalanceHistory toSave) throws SQLException{
        return  service.createBalanceHistory(toSave);
    }

    @PutMapping("/balance-history/{id}")
    public void updateBalanceHistory(@PathVariable("id") UUID id, @RequestBody BalanceHistory toUpdate) throws SQLException{
        service.updateBalanceHistory(id,toUpdate);
    }

    @DeleteMapping("/balance-history/{id}")
    public void deleteBalanceHistory(@PathVariable("id") UUID id) throws SQLException{
        service.deleteBalanceHistory(id);
    }
}

