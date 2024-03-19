package hei.school.digitalbankapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class Transfer {
    private int transferId;
    private int accountIdRecipient;
    private Double amount;
    private String transferReason;
    private Timestamp effectiveDate;
    private Timestamp registrationDate;
    private String label;
    private String status;
}
