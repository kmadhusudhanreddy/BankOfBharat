package BankApplication.demo.Repository;

import BankApplication.demo.Entity.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<AccountInformation , Integer> {

    @Query("select u from AccountInformation u WHERE u.emailId = :emailId ")
    AccountInformation findByMailId(@Param("emailId") String emailId) ;

    @Query("SELECT u FROM AccountInformation u WHERE u.phoneNo = :phoneNo")
    AccountInformation findByPhoneNo(@Param("phoneno") String phoneNo);

    @Query("SELECT u FROM AccopuntInformation u WHERE u.accountNumber = :accountNumber")
    AccountInformation findByAccountNo(@Param("accNo") String accNo);


    @Query("SELECT u FROM AccountInformation u WHERE u.accountNumber = :accountNumber AND u.password = :password ")
    AccountInformation findAccount(String accountNumber , String password);
}
