package hei.school.digitalbankapi;

import hei.school.digitalbankapi.Entity.Transaction;
import hei.school.digitalbankapi.Service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class StatementController {
        @Autowired
        private StatementService statementService;

        @GetMapping("/statement")
        public List<Transaction> getStatementOfAccount(
                @RequestParam UUID accountId,
                @RequestParam String startDate,
                @RequestParam String endDate) {
            return statementService.getStatementOfAccount(accountId, startDate, endDate);
        }
    }


