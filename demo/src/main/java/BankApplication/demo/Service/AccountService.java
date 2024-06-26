package BankApplication.demo.Service;

import BankApplication.demo.Dao.*;

public interface AccountService  {

    UserResponse createAccount(UserRequest  userRequest) ;
    UserResponse updateAccount(UserRequest  userRequest) ;


    AccountDeleteResponse deleteAccount(AccountDeleteRequest accountDeleteRequest);

    AccountDetailsResponse getAccountDetails(String accNo , String password) ;

     CreditResponse creditMoney(CreditMoneyRequest creditMoneyRequest);

     DebitResponse debitMoney(DebitMoneyRequest debitMoneyRequest) ;

     BalanceEnquiryResponse  balance_enquiry(BalanceEnquiryRequest balanceEnquiryRequest);

}
