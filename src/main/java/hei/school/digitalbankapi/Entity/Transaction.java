package hei.school.digitalbankapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private int transactionId;
    private Timestamp transactionDate;
    private Double amount;
    private String transactionType;
    private int accountId;
}
