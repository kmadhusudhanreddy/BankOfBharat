package BankApplication.demo.Dao;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsResponse {
    private String accountId ;
    private String accountHolderName ;
    private String phoneNo ;
    private String emailId ;
    private String gender ;
    private  String stateOfOrigin ;

}
