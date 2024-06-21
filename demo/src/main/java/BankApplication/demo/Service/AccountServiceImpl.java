package BankApplication.demo.Service;

import BankApplication.demo.Dao.*;
import BankApplication.demo.Entity.AccountInformation;
import BankApplication.demo.Exception.AccountNotFoundException;
import BankApplication.demo.Exception.EmailAlreadyExistException;
import BankApplication.demo.Exception.PhoneAlreadyFoundException;
import BankApplication.demo.Mapper.MapperToResponse;
import BankApplication.demo.Repository.AccountRepository;
import BankApplication.demo.Util.AccountDetailsGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class AccountServiceImpl implements  AccountService {

    @Autowired
    AccountRepository accountRepository ;
     @Autowired
    AccountDetailsGenerator accountDetailsGenerator ;
     @Autowired
    MapperToResponse mapperToResponse ;

     @Autowired
     AccountDetailsResponse accountDetailsResponse ;
    @Override
    public UserResponse createAccount(UserRequest userRequest) {

        String email = userRequest.getEmailId();
        String phone = userRequest.getPhoneNo();

        Optional<AccountInformation> existingMail = Optional.ofNullable(accountRepository.findByMailId(email));

        Optional<AccountInformation>  existingPhone =
                Optional.ofNullable(accountRepository.findByMailId(phone));

          if(existingMail.isPresent())
          {
             throw   new EmailAlreadyExistException("Email already exists");
          } else if (existingPhone.isPresent()) {
              throw new PhoneAlreadyFoundException("PhoneNo already Exists");
         }
          else{
               String accountNumber =  accountDetailsGenerator.generateAccountNumber();
               String passwordPin =  accountDetailsGenerator.generatePassword();
               AccountInformation accountInformation = AccountInformation.builder()
                       .accountHolderName(userRequest.getAccountHolderName())
                       .emailId(userRequest.getEmailId())
                       .phoneNo(userRequest.getPhoneNo())
                       .gender(userRequest.getGender())
                       .stateOfOrigin(userRequest.getStateOfOrigin())
                       .accountNumber(accountNumber)
                       .password(passwordPin)
                       .build();
               accountRepository.save(accountInformation);
           UserResponse userResponse =     mapperToResponse.userInformationToUserRequest(accountInformation);
           userResponse.setMessage("ACCOUNT CREATED SUCCESSFULLY");

           return userResponse ;
          }

    }

    @Override
    public UserResponse updateAccount(UserRequest userRequest) {

        String accNo = userRequest.getAccountNumber();
        Optional<AccountInformation>  existingAccount = Optional.ofNullable(accountRepository.findByAccountNo(accNo));
        if(existingAccount.isPresent()){
              AccountInformation accountInformationUpdateDetails = AccountInformation.builder()
                      .accountHolderName(userRequest.getAccountHolderName())
                      .accountNumber(userRequest.getAccountNumber())
                      .emailId(userRequest.getEmailId())
                      .password(userRequest.getPassword())
                      .gender(userRequest.getGender())
                      .phoneNo(userRequest.getPhoneNo())
                      .stateOfOrigin(userRequest.getStateOfOrigin())
                      .build();
              accountRepository.save(accountInformationUpdateDetails);
              UserResponse userResponse = mapperToResponse.userInformationToUserRequest(accountInformationUpdateDetails);
              userResponse.setMessage("Account Updated Successfully");

              return userResponse ;

        }
        else {
            throw new AccountNotFoundException("Account Already Exists");
        }


    }


    @Override
   public AccountDeleteResponse deleteAccount(AccountDeleteRequest accountDeleteRequest) {
      String accountNumber =   accountDeleteRequest.getAccountNumber() ;
      String emailId = accountDeleteRequest.getEmailId() ;
      String password = accountDeleteRequest.getPassword() ;

      AccountInformation accountInformation = accountRepository.findAccount(accountNumber , password);

          if(accountInformation != null){
              accountRepository.delete(accountInformation);

              return new AccountDeleteResponse("Account with " + accountNumber +   "deleted Sucessfully") ;
          }
          else {

              throw  new AccountNotFoundException("Account not found with given details please ceck the details");
          }
    }

    @Override
    public AccountDetailsResponse getAccountDetails(String accNo , String password) {

      AccountInformation accountInformation = accountRepository.findAccount(accNo , password) ;
      if(accountInformation!= null){
          accountDetailsResponse.setAccountHolderName(accountInformation.getAccountHolderName());
          accountDetailsResponse.setEmailId(accountInformation.getEmailId());
          accountDetailsResponse.setGender(accountInformation.getGender());
          accountDetailsResponse.setPhoneNo(accountInformation.getPhoneNo());
          accountDetailsResponse.setStateOfOrigin(accountInformation.getStateOfOrigin());
          accountDetailsResponse.setAccountId(accountInformation.getAccountId());

          return accountDetailsResponse ;
      }
      else{
          throw new AccountNotFoundException("Account not found check information correctly");
      }
    }


}
