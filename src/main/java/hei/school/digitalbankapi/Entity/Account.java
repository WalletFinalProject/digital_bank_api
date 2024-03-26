package hei.school.digitalbankapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
public class Account {
    private int idAccount;
    private boolean authorizeCredits;
    private Date creationDate;
    private Date updateDate;
    private String clientName;
    private String clientFirstname;
    private Date birthDate;
    private Double netMonthlySalary;
    private int idTransaction;
    private Double balance;
    private Double creditAmount;
}
