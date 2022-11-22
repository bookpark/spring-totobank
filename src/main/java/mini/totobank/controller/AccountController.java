package mini.totobank.controller;

import lombok.RequiredArgsConstructor;
import mini.totobank.domain.Account;
import mini.totobank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/api/make-account")
    public ResponseEntity<String> makeAccount(Account acc) throws Exception {
        ResponseEntity<String> res = null;
        System.out.println(acc);
        try {
            accountService.makeAccount(acc);
            res = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/api/inquire-account")
    public ResponseEntity<Account> inquireAccount(@RequestParam("accountNumber") String accountNumber) throws Exception {
        ResponseEntity<Account> res = null;
        try {
            Account acc = accountService.inquireAccount(accountNumber);
            res = new ResponseEntity<Account>(acc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Account>(new Account(), HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/api/check-duplicate")
    public ResponseEntity<Boolean> checkDuplicate(@RequestParam("accountNumber") String accountNumber) {
        ResponseEntity<Boolean> res = null;
        try {
            Boolean isDuplicate = accountService.checkDuplicate(accountNumber);
            res = new ResponseEntity<Boolean>(isDuplicate, HttpStatus.OK);
        } catch (Exception e) {
            res = new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/api/deposit")
    public ResponseEntity<Integer> deposit(@RequestParam("accountNumber") String accountNumber,
                                          @RequestParam("money") Integer money) {
        ResponseEntity<Integer> res = null;
        try {
            Integer balance = accountService.deposit(accountNumber, money);
            res = new ResponseEntity<Integer>(balance, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/api/withdraw")
    public ResponseEntity<Integer> withdraw(@RequestParam("accountNumber") String accountNumber,
                                           @RequestParam("money") Integer money) {
        ResponseEntity<Integer> res = null;
        try {
            Integer balance = accountService.withdraw(accountNumber, money);
            res = new ResponseEntity<Integer>(balance, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/api/transfer")
    public ResponseEntity<Integer> transfer(@RequestParam("accountNumberW") String accountNumberW,
                                            @RequestParam("accountNumberD") String accountNumberD,
                                            @RequestParam("money") Integer money) {
        ResponseEntity<Integer> res = null;
        try {
            Integer balance = accountService.transfer(accountNumberW, accountNumberD, money);
            res = new ResponseEntity<Integer>(balance, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
        }
        return res;
    }

}
