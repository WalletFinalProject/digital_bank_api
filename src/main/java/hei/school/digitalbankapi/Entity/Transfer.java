package hei.school.digitalbankapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Transfer {
    private UUID idTransfert;
    private UUID accountIdRecipient;
    private Double amount;
    private String transferReason;
    private Timestamp effectiveDate;
    private Timestamp registrationDate;
    private String label;
    private String status;
    private UUID idBalanceHistory;
}
