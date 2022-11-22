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

    public Integer deposit(String accountNumber, Integer money) throws Exception {
        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if(byAccountNumber.isEmpty()) throw new Exception("계좌번호 오류");
        Account acc = byAccountNumber.get();
        acc.deposit(money);
        accountRepository.save(acc);
        return acc.getBalance();
    }

    public Integer withdraw(String accountNumber, Integer money) throws Exception {
        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if(byAccountNumber.isEmpty()) throw new Exception("계좌번호 오류");
        Account acc = byAccountNumber.get();
        acc.withdraw(money);
        accountRepository.save(acc);
        return acc.getBalance();
    }

}
