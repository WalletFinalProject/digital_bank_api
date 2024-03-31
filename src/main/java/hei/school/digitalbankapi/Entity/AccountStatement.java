package hei.school.digitalbankapi.Entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AccountStatement {
    private UUID accountStatementId;
    private Timestamp accountStatementDate;
    private String reference;
    private String motif;
    private double creditMGA;
    private double debitMGA;
    private double balance;
    private UUID idAccount;
}
