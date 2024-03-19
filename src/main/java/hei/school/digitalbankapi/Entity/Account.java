package hei.school.digitalbankapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
public class Account {
    private int accountId;
    private String clientName;
    private String clientFirstName;
    private Date birthDate;
    private Double monthlyPay;
    private String overdrawnStatus;
    private String accountType;
}
