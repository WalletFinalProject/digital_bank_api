package hei.school.digitalbankapi.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BalanceHistory {
    private UUID idBalance;
    private Double moneyLoan;
    private Double loanInterest;
}
