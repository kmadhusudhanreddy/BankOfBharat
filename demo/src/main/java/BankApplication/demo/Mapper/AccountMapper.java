package BankApplication.demo.Mapper;


import BankApplication.demo.Dao.UserResponse;
import BankApplication.demo.Entity.AccountInformation;

public class AccountMapper {
    public static AccountInformation mapToAccount(UserResponse userResponse){
        return  new AccountInformation(
                userResponse.getAccountId(),
                userResponse.getAccountHolderName(),
                userResponse.getPhoneNo(),
                userResponse.getEmailId(),
                userResponse.getGender(),
                userResponse.getStateOfOrigin(),
                userResponse.getAccountNumber()
        );
    }

    public static UserResponse mapToBankDto(AccountInformation accountInformation){
        return new UserResponse(
                accountInformation.getAccountId(),
                accountInformation.getAccountHolderName(),
                accountInformation.getEmailId(),
                accountInformation.getGender(),
                accountInformation.getStateOfOrigin(),
                accountInformation.getAccountNumber(),
                accountInformation.getPassword()
        );
    }

}
