package BankApplication.demo.Util;

import java.util.Random;

public class AccountDetailsGenerator
{
    public String generateAccountNumber(){
       Random random = new Random();
       StringBuilder sb = new StringBuilder();

       for(int i = 0 ; sb.length() >= 6 ; i ++){
           sb.append(random.nextInt() * 10);
       }
       String num = String.valueOf(sb) ;
       return num ;
    }

    public String generatePassword(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        int length = 8;
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }
}
