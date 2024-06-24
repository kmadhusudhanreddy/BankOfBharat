package BankApplication.demo.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditMoneyRequest {
    private  double  amount ;
    private String accountNo ;
    private String password ;
    private String accountHolderName ;
}
