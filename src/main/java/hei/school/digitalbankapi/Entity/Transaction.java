package hei.school.digitalbankapi.Entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Transaction {
    private UUID idTransaction;
    private Timestamp transactionDate;
    private Double amount;
    private String transactionType;
    private String label;
    private UUID idAccount;
}
