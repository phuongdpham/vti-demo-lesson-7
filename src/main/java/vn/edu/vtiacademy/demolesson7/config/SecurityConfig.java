package vn.edu.vtiacademy.demolesson7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vn.edu.vtiacademy.demolesson7.model.AccountNotFoundException;
import vn.edu.vtiacademy.demolesson7.model.ErrorCode;
import vn.edu.vtiacademy.demolesson7.repository.AccountRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain basicAuthFilterChain(HttpSecurity http,
                                                    AccountRepository accountRepository,
                                                    MyBasicAuthenticationEntryPoint authenticationEntryPoint,
                                                    MyAccessDeniedHandler accessDeniedHandler) throws Exception {
        http
                .cors(withDefaults())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .accessDeniedHandler(accessDeniedHandler)
                                .authenticationEntryPoint(authenticationEntryPoint))
                .httpBasic(withDefaults())
                .userDetailsService((String username) -> accountRepository.findByUsername(username).orElseThrow(() -> new AccountNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND, "Account not found with username: " + username)))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
