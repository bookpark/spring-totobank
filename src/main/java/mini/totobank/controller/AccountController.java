package mini.totobank.controller;

import lombok.RequiredArgsConstructor;
import mini.totobank.domain.Account;
import mini.totobank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
