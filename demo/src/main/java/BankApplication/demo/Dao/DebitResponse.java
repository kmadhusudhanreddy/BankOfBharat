package BankApplication.demo.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitResponse {
    private String senderAccountNumber;
    private double totalBalance;
    private double debitedMoney;
    private String accountHolderName;
    private  String message ;
}
