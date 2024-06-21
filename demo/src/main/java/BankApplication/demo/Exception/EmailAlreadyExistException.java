package BankApplication.demo.Exception;

public class EmailAlreadyExistException extends  RuntimeException{
    String message ;
    public EmailAlreadyExistException(String message){
        this.message = message ;
    }
}
