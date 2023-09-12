package vn.edu.vtiacademy.demolesson7.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.vtiacademy.demolesson7.model.Account;
import vn.edu.vtiacademy.demolesson7.model.AccountFilter;
import vn.edu.vtiacademy.demolesson7.model.AccountNotFoundException;
import vn.edu.vtiacademy.demolesson7.model.ErrorCode;
import vn.edu.vtiacademy.demolesson7.repository.AccountRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainAccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account getByUsername(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND, "Account not found with given username: " + username));
    }

    @Override
    public Page<Account> findAll(AccountFilter filter, Pageable pageable) {
        return accountRepository.findAll(filter, pageable);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND, "Account not found"));
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }
}
