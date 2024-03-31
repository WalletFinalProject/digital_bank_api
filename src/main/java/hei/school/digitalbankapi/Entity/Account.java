package hei.school.digitalbankapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private UUID idAccount;
    private boolean authorizeCredits;
    private Date creationDate;
    private Date updateDate;
    private String clientName;
    private String clientFirstname;
    private Date birthDate;
    private Double netMonthlySalary;
    private Double balance;
    private Double creditAmount;
}
