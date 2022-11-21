package mini.totobank;

import mini.totobank.domain.Account;
import mini.totobank.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TotobankApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void addAccount() {
        Account account = new Account();
        account.setAccountNumber("13124123");
        account.setName("박부기");
        account.setGrade("VIP");
        account.setBalance(0);
    }

    @Test
    void contextLoads() {
    }

}
