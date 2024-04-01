package hei.school.digitalbankapi.Controller;

import hei.school.digitalbankapi.Entity.AccountStatement;
import hei.school.digitalbankapi.Entity.BalanceHistory;
import hei.school.digitalbankapi.Service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class BalanceController {
    private BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public static BalanceController createBalanceController(BalanceService balanceService) {
        return new BalanceController(balanceService);
    }

    @GetMapping("/balance-history")
    public List<BalanceHistory> getAllBalanceHistory() throws SQLException {
        return balanceService.getBalanceHistory();
    }

    @GetMapping("/balance-history/{id}")
    public ResponseEntity<List<BalanceHistory>> getBalanceHistoryById(@PathVariable("id") UUID id) throws SQLException {
        List<BalanceHistory> balanceHistories = balanceService.getBalanceHistoryById(id);
        if (balanceHistories == null || balanceHistories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(balanceHistories);
    }

    @PostMapping("/balance-history")
    public BalanceHistory createBalanceHistory(@RequestBody BalanceHistory toSave) throws SQLException{
        return  balanceService.createBalanceHistory(toSave);
    }

    @PutMapping("/balance-history/{id}")
    public void updateBalanceHistory(@PathVariable("id") UUID id, @RequestBody BalanceHistory toUpdate) throws SQLException{
        balanceService.updateBalanceHistory(id,toUpdate);
    }

    @DeleteMapping("/balance-history/{id}")
    public void deleteBalanceHistory(@PathVariable("id") UUID id) throws SQLException{
        balanceService.deleteBalanceHistory(id);
    }
}

