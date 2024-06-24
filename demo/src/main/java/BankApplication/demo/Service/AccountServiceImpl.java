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
    AccountRepository accountRepository;
    @Autowired
    AccountDetailsGenerator accountDetailsGenerator;
    @Autowired
    MapperToResponse mapperToResponse;

    @Autowired
    AccountDetailsResponse accountDetailsResponse;

    @Autowired
    BalanceEnquiryResponse balanceEnquiryResponse;

    @Override
    public UserResponse createAccount(UserRequest userRequest) {

        String email = userRequest.getEmailId();
        String phone = userRequest.getPhoneNo();

        Optional<AccountInformation> existingMail = Optional.ofNullable(accountRepository.findByMailId(email));

        Optional<AccountInformation> existingPhone =
                Optional.ofNullable(accountRepository.findByMailId(phone));

        if (existingMail.isPresent()) {
            throw new EmailAlreadyExistException("Email already exists");
        } else if (existingPhone.isPresent()) {
            throw new PhoneAlreadyFoundException("PhoneNo already Exists");
        } else {
            String accountNumber = accountDetailsGenerator.generateAccountNumber();
            String passwordPin = accountDetailsGenerator.generatePassword();
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
            UserResponse userResponse = mapperToResponse.userInformationToUserRequest(accountInformation);
            userResponse.setMessage("ACCOUNT CREATED SUCCESSFULLY");

            return userResponse;
        }

    }

    @Override
    public UserResponse updateAccount(UserRequest userRequest) {

        String accNo = userRequest.getAccountNumber();
        Optional<AccountInformation> existingAccount = Optional.ofNullable(accountRepository.findByAccountNo(accNo));
        if (existingAccount.isPresent()) {
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

            return userResponse;

        } else {
            throw new AccountNotFoundException("Account Already Exists");
        }


    }


    @Override
    public AccountDeleteResponse deleteAccount(AccountDeleteRequest accountDeleteRequest) {
        String accountNumber = accountDeleteRequest.getAccountNumber();
        String emailId = accountDeleteRequest.getEmailId();
        String password = accountDeleteRequest.getPassword();

        AccountInformation accountInformation = accountRepository.findAccount(accountNumber, password);

        if (accountInformation != null) {
            accountRepository.delete(accountInformation);

            return new AccountDeleteResponse("Account with " + accountNumber + "deleted Sucessfully");
        } else {

            throw new AccountNotFoundException("Account not found with given details please ceck the details");
        }
    }

    @Override
    public AccountDetailsResponse getAccountDetails(String accNo, String password) {

        AccountInformation accountInformation = accountRepository.findAccount(accNo, password);
        if (accountInformation != null) {
            accountDetailsResponse.setAccountHolderName(accountInformation.getAccountHolderName());
            accountDetailsResponse.setEmailId(accountInformation.getEmailId());
            accountDetailsResponse.setGender(accountInformation.getGender());
            accountDetailsResponse.setPhoneNo(accountInformation.getPhoneNo());
            accountDetailsResponse.setStateOfOrigin(accountInformation.getStateOfOrigin());
            accountDetailsResponse.setAccountId(accountInformation.getAccountId());

            return accountDetailsResponse;
        } else {
            throw new AccountNotFoundException("Account not found check information correctly");
        }
    }

    @Override
    public CreditResponse creditMoney(CreditMoneyRequest creditMoneyRequest) {
        String accNo= creditMoneyRequest.getAccountNo();
        String password = creditMoneyRequest.getPassword();
        double addMoney = creditMoneyRequest.getAmount();
        AccountInformation accountInformation = accountRepository.findAccount(accNo , password);
        if(accountInformation != null){
            double creditMoney = addMoney ;
            double availableBalance = accountInformation.getAccountBalance() + creditMoney ;
            accountInformation.setAccountBalance(availableBalance);
            accountRepository.save(accountInformation);

            CreditResponse creditResponse = new CreditResponse();
            creditResponse.setMessage("credited money successfully");
            creditResponse.setMoney(accountInformation.getAccountBalance());
            return creditResponse ;
        }
        else {
            throw new AccountNotFoundException("Account not avilable please check your details correctly");
        }
    }

    @Override
    public DebitResponse debitMoney(DebitMoneyRequest debitMoneyRequest) {
        String senderAccNo = debitMoneyRequest.getSenderAccNo();
        String receiverAccNo = debitMoneyRequest.getReceiverAccNo();
        String password = debitMoneyRequest.getPassword();
        double debitMoney = debitMoneyRequest.getDebitMoney();
        AccountInformation senderAccountInformation = accountRepository.findAccount(senderAccNo, password);
        AccountInformation receiverAccountInformation = accountRepository.findByAccountNo(receiverAccNo);

        DebitResponse debitResponse = new DebitResponse();
        if (senderAccountInformation != null && receiverAccountInformation != null) {
            double avail_balance = senderAccountInformation.getAccountBalance();
            double receiver_avail_balance = receiverAccountInformation.getAccountBalance();
            if (avail_balance >= debitMoney) {
                avail_balance = avail_balance - debitMoney;
                senderAccountInformation.setAccountBalance(avail_balance);
                accountRepository.save(senderAccountInformation);
                receiver_avail_balance = receiver_avail_balance + debitMoney;
                senderAccountInformation.setAccountBalance(receiver_avail_balance);
                receiverAccountInformation.setAccountBalance(receiver_avail_balance);
                accountRepository.save(receiverAccountInformation);
                debitResponse.setDebitedMoney(debitMoney);
                debitResponse.setSenderAccountNumber(senderAccountInformation.getAccountNumber());
                debitResponse.setAccountHolderName(senderAccountInformation.getAccountHolderName());
                debitResponse.setTotalBalance(senderAccountInformation.getAccountBalance());
                debitResponse.setMessage("MONEY_SENT_SUCCESSFULLY");

                return debitResponse;
            } else {
                  debitResponse.setMessage("not available balance");
            }
        }
        else {
            throw new AccountNotFoundException("there is an error in receiver account no ,  available are wrong number entered");
        }

        return debitResponse ;
    }

    @Override
    public BalanceEnquiryResponse balance_enquiry(BalanceEnquiryRequest balanceEnquiryRequest) {
        String accNo = balanceEnquiryRequest.getAccountNo();
        String password = balanceEnquiryRequest.getPassword();
        BalanceEnquiryResponse balanceEnquiryResponse1 = new BalanceEnquiryResponse();
        AccountInformation accountInformation = accountRepository.findAccount(accNo , password) ;
        if (accountInformation != null){
            double balance = accountInformation.getAccountBalance();
            balanceEnquiryResponse1.setAccNo(accountInformation.getAccountNumber());
            balanceEnquiryResponse1.setAccountHolderName(accountInformation.getAccountHolderName());
            balanceEnquiryResponse1.setBalance(balance);
            balanceEnquiryResponse1.setMessage("TPUR _BALANCE IS : + {balance} + " );

            return balanceEnquiryResponse1;
        }
        else {
            throw new AccountNotFoundException("Account not available ") ;
        }
    }


}





