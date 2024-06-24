package BankApplication.demo.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceEnquiryResponse {
    private String accNo ;
    private  String accountHolderName ;
    private  String message ;
   private double balance ;
}
