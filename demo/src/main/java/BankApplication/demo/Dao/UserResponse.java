package BankApplication.demo.Dao;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String accountId ;
    private String accountHolderName ;
    private String phoneNo ;
    private String emailId ;
    private String gender ;
    private  String stateOfOrigin ;
    private String accountNumber ;
    private  String password ;
    private  String message ;
}
