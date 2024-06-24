package BankApplication.demo.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitMoneyRequest {
    private String senderAccNo ;
    private String receiverAccNo ;
    private String password ;
    private double debitMoney ;

}
