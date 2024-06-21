package BankApplication.demo.Exception;

public class PhoneAlreadyFoundException extends  RuntimeException{
    String message ;
    public PhoneAlreadyFoundException(String message){
        this.message = message ;
    }
}
