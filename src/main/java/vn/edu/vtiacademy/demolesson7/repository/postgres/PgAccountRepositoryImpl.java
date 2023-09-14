package vn.edu.vtiacademy.demolesson7.repository.postgres;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vn.edu.vtiacademy.demolesson7.model.Account;
import vn.edu.vtiacademy.demolesson7.model.AccountExistedException;
import vn.edu.vtiacademy.demolesson7.model.AccountFilter;
import vn.edu.vtiacademy.demolesson7.model.ErrorCode;
import vn.edu.vtiacademy.demolesson7.repository.AccountRepository;

import java.util.Optional;

@Component
@Primary
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PgAccountRepositoryImpl implements AccountRepository {
    AccountJpaRepository repository;

    @Override
    public Optional<Account> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Page<Account> findAll(AccountFilter filter, Pageable pageable) {
        return repository.findAll(AccountSpecification.withFilter(filter), pageable);
    }

    @Override
    public Account save(Account account) {
        try {
            return repository.save(account);
        } catch (DataIntegrityViolationException ex) {
            var cause = ex.getCause();
            if (cause instanceof ConstraintViolationException cve && cve.getConstraintName().equals("account_username_uindex")) {
                throw new AccountExistedException(ErrorCode.ACCOUNT_EXISTED, "Username already exists");
            }

            throw ex;
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Account account) {
        repository.delete(account);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
