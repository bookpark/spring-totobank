package mini.totobank.service;

import lombok.RequiredArgsConstructor;
import mini.totobank.domain.Account;
import mini.totobank.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void makeAccount(Account acc) {
        acc.setBalance(0);
        accountRepository.save(acc);
    }

}
