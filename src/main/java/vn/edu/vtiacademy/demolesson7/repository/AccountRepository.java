package vn.edu.vtiacademy.demolesson7.repository;

import vn.edu.vtiacademy.demolesson7.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByUsername(String username);
}
