package mini.totobank.service;

import lombok.RequiredArgsConstructor;
import mini.totobank.domain.Account;
import mini.totobank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void makeAccount(Account acc) {
        acc.setBalance(0);
        accountRepository.save(acc);
    }

    public Account inquireAccount(String accountNumber) throws Exception {
        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if (!byAccountNumber.isPresent()) throw new Exception("계좌번호 오류");
        return byAccountNumber.get();
    }

    public Boolean checkDuplicate(String accountNumber) throws Exception {
        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        return byAccountNumber.isPresent();
    }

}
