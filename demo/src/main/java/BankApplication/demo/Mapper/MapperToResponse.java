package BankApplication.demo.Mapper;

import BankApplication.demo.Dao.UserRequest;
import BankApplication.demo.Dao.UserResponse;
import BankApplication.demo.Entity.AccountInformation;

public class MapperToResponse {
    public UserResponse  userInformationToUserRequest(AccountInformation accountInformation){
        UserResponse userResponse = new UserResponse();
        userResponse.setAccountId(accountInformation.getAccountId());
        userResponse.setAccountHolderName(accountInformation.getAccountHolderName());
        userResponse.setPhoneNo(accountInformation.getPhoneNo());
        userResponse.setEmailId(accountInformation.getEmailId());
        userResponse.setGender(accountInformation.getEmailId());
        userResponse.setStateOfOrigin(accountInformation.getStateOfOrigin());
        userResponse.setAccountNumber(accountInformation.getAccountNumber());
        userResponse.setPassword(accountInformation.getPassword());

        return userResponse;
    }
}
