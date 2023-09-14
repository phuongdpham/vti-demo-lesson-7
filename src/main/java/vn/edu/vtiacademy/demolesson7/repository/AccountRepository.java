package vn.edu.vtiacademy.demolesson7.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.vtiacademy.demolesson7.model.Account;
import vn.edu.vtiacademy.demolesson7.model.AccountFilter;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByUsername(String username);

    Page<Account> findAll(AccountFilter filter, Pageable pageable);

    Account save(Account account);

    Optional<Account> findById(Long id);

    void delete(Account account);

    boolean existsByUsername(String username);
}
