package vn.edu.vtiacademy.demolesson7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vn.edu.vtiacademy.demolesson7.repository.DepartmentRepository;
import vn.edu.vtiacademy.demolesson7.repository.postgres.DepartmentJpaRepository;
import vn.edu.vtiacademy.demolesson7.repository.postgres.PgDepartmentRepositoryImpl;
import vn.edu.vtiacademy.demolesson7.service.DomainDepartmentServiceImpl;
import vn.edu.vtiacademy.demolesson7.service.DepartmentService;

@Configuration
@EnableJpaRepositories(basePackages = "vn.edu.vtiacademy.demolesson7.repository")
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
}
