package BankApplication.demo.Dao;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDeleteRequest {
    private  String emailId ;
    private  String accountNumber ;
    private  String password ;
}
