package hei.school.digitalbankapi.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BalanceHistory {
    private int idBalance;
    private Double moneyLoan;
    private Double loanInterest;
}
