package BankApplication.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_INFO")
public class AccountInformation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountId ;
    private String accountHolderName ;
    private String phoneNo ;
    @Column(name = "email_Id" , nullable = false , unique = true )
    private String emailId ;
    private String gender ;
    private  String stateOfOrigin ;
    private String accountNumber ;
    private  String password ;
    private double accountBalance ;

}
