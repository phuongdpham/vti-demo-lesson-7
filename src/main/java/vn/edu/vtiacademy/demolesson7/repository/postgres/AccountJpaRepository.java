package vn.edu.vtiacademy.demolesson7.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.vtiacademy.demolesson7.model.Account;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
