package mini.totobank.service;

import lombok.RequiredArgsConstructor;
import mini.totobank.domain.Account;
import mini.totobank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void makeAccount(Account acc) {
        acc.setBalance(0);
        accountRepository.save(acc);
    }

    // 계좌 조회
    public Account inquireAccount(String accountNumber) throws Exception {
        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if (!byAccountNumber.isPresent()) throw new Exception("계좌번호 오류");
        return byAccountNumber.get();
    }

    // 중복 체크
    public Boolean checkDuplicate(String accountNumber) throws Exception {
        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        return byAccountNumber.isPresent();
    }

    // 입금
    public Integer deposit(String accountNumber, Integer money) throws Exception {
        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if(byAccountNumber.isEmpty()) throw new Exception("계좌번호 오류");
        Account acc = byAccountNumber.get();
        acc.deposit(money);
        accountRepository.save(acc);
        return acc.getBalance();
    }

    // 출금
    public Integer withdraw(String accountNumber, Integer money) throws Exception {
        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if(byAccountNumber.isEmpty()) throw new Exception("계좌번호 오류");
        Account acc = byAccountNumber.get();
        acc.withdraw(money);
        accountRepository.save(acc);
        return acc.getBalance();
    }

    // 이체
    public Integer transfer(String accountNumberW, String accountNumberD, Integer money) throws Exception {
        Optional<Account> accountW = accountRepository.findByAccountNumber(accountNumberW);
        Optional<Account> accountD = accountRepository.findByAccountNumber(accountNumberD);
        if (accountW.isEmpty() || accountD.isEmpty()) throw new Exception("계좌번호 오류");
        Account accWithdraw = accountW.get();
        accWithdraw.withdraw(money);
        Account accDeposit = accountD.get();
        accDeposit.deposit(money);
        accountRepository.save(accWithdraw);
        accountRepository.save(accDeposit);
        return accWithdraw.getBalance();
    }

    // 계좌 목록
    public List<Account> accountList() {
        return accountRepository.findAll();
    }

}
