package BankApplication.demo.Controller;

import BankApplication.demo.Dao.*;
import BankApplication.demo.Exception.AccountNotFoundException;
import BankApplication.demo.Service.AccountService;
import BankApplication.demo.Service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Account")
public class AccountController {
    @Autowired
    AccountServiceImpl accountService ;
    @PostMapping
    public ResponseEntity<UserResponse> createAccount(@RequestBody UserRequest userRequest){
           UserResponse userResponse = accountService.createAccount(userRequest);
           return  new ResponseEntity<UserResponse>(userResponse , HttpStatus.CREATED);
    }

    @PutMapping
    public  ResponseEntity<UserResponse>  updateAccount(@RequestBody UserRequest userRequest){
              UserResponse userResponse ;
              try {
                  userResponse = accountService.updateAccount(userRequest);
              }
              catch (AccountNotFoundException e){
                  throw  new RuntimeException(e) ;
              }
              return  new ResponseEntity<UserResponse>(userResponse , HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<AccountDeleteResponse> deleteAccount(@RequestBody AccountDeleteRequest accountDeleteRequest){
              AccountDeleteResponse accountDeleteResponse =  accountService.deleteAccount(accountDeleteRequest);
            return new ResponseEntity<>(accountDeleteResponse , HttpStatus.ACCEPTED) ;
    }

    @GetMapping("/get-account-details/{accountNo}/{password}")
    public  ResponseEntity<AccountDetailsResponse>  getAccDetails(@PathVariable String  accountNo , @PathVariable  String password){
        AccountDetailsResponse accountDetailsResponse = accountService.getAccountDetails(accountNo , password);
        return  new ResponseEntity<AccountDetailsResponse>(accountDetailsResponse , HttpStatus.ACCEPTED) ;
    }
}
