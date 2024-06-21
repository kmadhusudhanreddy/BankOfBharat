package BankApplication.demo.Dao;

import lombok.Data;

@Data
public class UserRequest {
    private String accountId ;
    private String accountHolderName ;
    private String phoneNo ;
    private String emailId ;
    private String gender ;
    private  String stateOfOrigin ;
    private String accountNumber ;
    private  String password ;

}
