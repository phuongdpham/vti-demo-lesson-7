package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.vtiacademy.demolesson7.controller.validation.AdminAccountReq;
import vn.edu.vtiacademy.demolesson7.controller.validation.PageableCheck;
import vn.edu.vtiacademy.demolesson7.model.AccountFilter;
import vn.edu.vtiacademy.demolesson7.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
public class AccountController {
    AccountService service;
    AccountMapper mapper;
    PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public Page<AccountResp> findAll(@ModelAttribute @ParameterObject AccountFilter filter, @ParameterObject @PageableCheck Pageable pageable) {
        return service.findAll(filter, pageable)
                .map(mapper::toAccountResp);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResp create(@RequestBody @Valid AdminAccountReq req) {
        var account = mapper.toAccount(req);
        var rawPwd = generatePwd();
        account.setPassword(passwordEncoder.encode(rawPwd));
        account.setRole(req.role());
        var res = mapper.toAccountResp(service.save(account));

        notifyPassword(account.getUsername(), rawPwd);

        return res;
    }

    private void notifyPassword(String username, String rawPwd) {
        log.info("Send password to {} with password {}", username, rawPwd);
    }

    private String generatePwd() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public AccountResp findById(@PathVariable Long id) {
        return service.findById(id)
                .transform(mapper::toAccountResp);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public AccountResp update(@PathVariable Long id, @RequestBody @Valid AccountUpdateReq req) {
        var account = service.findById(id);
        mapper.update(req, account);
        return mapper.toAccountResp(service.save(account));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.findById(id)
                .transform(a -> {
                    a.setEnable(false);
                    return service.save(a);
                });
    }

    @PostMapping("/disable-batch")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@RequestBody @NotEmpty List<Long> ids) {
        ids.forEach(
                id -> service.findById(id)
                        .transform(a -> {
                            a.setEnable(false);
                            return service.save(a);
                        })
        );
    }

}
