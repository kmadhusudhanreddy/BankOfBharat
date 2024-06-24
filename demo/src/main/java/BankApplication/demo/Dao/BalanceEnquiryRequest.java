package BankApplication.demo.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class BalanceEnquiryRequest {
   private String accountNo ;
    private  String password ;
}
