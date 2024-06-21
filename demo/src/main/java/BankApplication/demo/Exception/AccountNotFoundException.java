package BankApplication.demo.Exception;

public class AccountNotFoundException extends RuntimeException {
    String message ;
    public AccountNotFoundException(String message){
         this.message = message ;
    }
}
