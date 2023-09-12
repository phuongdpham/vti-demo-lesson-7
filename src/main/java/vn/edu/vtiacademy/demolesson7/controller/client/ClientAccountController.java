package vn.edu.vtiacademy.demolesson7.controller.client;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.edu.vtiacademy.demolesson7.controller.AccountMapper;
import vn.edu.vtiacademy.demolesson7.controller.AccountReq;
import vn.edu.vtiacademy.demolesson7.controller.AccountResp;
import vn.edu.vtiacademy.demolesson7.controller.AccountUpdateReq;
import vn.edu.vtiacademy.demolesson7.model.Role;
import vn.edu.vtiacademy.demolesson7.service.AccountService;

import java.security.Principal;

@RestController
@RequestMapping("/client/accounts")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientAccountController {
    AccountService service;
    AccountMapper mapper;
    PasswordEncoder passwordEncoder;

    @PostMapping
    public AccountResp register(@RequestBody @Valid AccountReq req) {
        var account = mapper.toAccount(req);
        account.setPassword(passwordEncoder.encode(req.password()));
        account.setRole(Role.USER);
        return mapper.toAccountResp(service.save(account));
    }

    @GetMapping("me")
    @PreAuthorize("hasAnyAuthority('USER')")
    public AccountResp getMe(Principal principal) {
        return service.getByUsername(principal.getName())
                .transform(mapper::toAccountResp);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public AccountResp update(Principal principal, @RequestBody @Valid AccountUpdateReq req) {
        var account = service.getByUsername(principal.getName());
        mapper.update(req, account);
        return mapper.toAccountResp(service.save(account));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Principal principal) {
        service.getByUsername(principal.getName())
                .transform(a -> {
                    a.setEnable(false);
                    return service.save(a);
                });
    }

    //TODO: add endpoint enable account
}
