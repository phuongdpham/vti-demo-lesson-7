package vn.edu.vtiacademy.demolesson7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.edu.vtiacademy.demolesson7.repository.AccountRepository;
import vn.edu.vtiacademy.demolesson7.repository.DepartmentRepository;
import vn.edu.vtiacademy.demolesson7.repository.postgres.DepartmentJpaRepository;
import vn.edu.vtiacademy.demolesson7.repository.postgres.PgDepartmentRepositoryImpl;
import vn.edu.vtiacademy.demolesson7.service.AccountService;
import vn.edu.vtiacademy.demolesson7.service.DepartmentService;
import vn.edu.vtiacademy.demolesson7.service.DomainAccountServiceImpl;
import vn.edu.vtiacademy.demolesson7.service.DomainDepartmentServiceImpl;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaRepositories(basePackages = "vn.edu.vtiacademy.demolesson7.repository")
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
public class AppConfig {

    @Bean
    DepartmentService departmentService(DepartmentRepository repository) {
        return new DomainDepartmentServiceImpl(repository);
    }

    @Bean
    @Primary
    DepartmentRepository departmentRepository(DepartmentJpaRepository repository) {
        return new PgDepartmentRepositoryImpl(repository);
    }

    @Bean
    DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

    // create auditorAware bean get principal from security context and return username
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.empty();
            }

            return Optional.of(authentication.getName());
        };

    }

    @Bean
    AccountService accountService(AccountRepository repository) {
        return new DomainAccountServiceImpl(repository);
    }
}
