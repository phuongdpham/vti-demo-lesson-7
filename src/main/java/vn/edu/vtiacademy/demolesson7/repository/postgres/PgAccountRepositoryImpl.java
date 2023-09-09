package vn.edu.vtiacademy.demolesson7.repository.postgres;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vn.edu.vtiacademy.demolesson7.model.Account;
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
}
