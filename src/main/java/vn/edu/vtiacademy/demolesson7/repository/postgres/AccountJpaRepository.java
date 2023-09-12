package vn.edu.vtiacademy.demolesson7.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.vtiacademy.demolesson7.model.Account;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByUsername(String username);
}
