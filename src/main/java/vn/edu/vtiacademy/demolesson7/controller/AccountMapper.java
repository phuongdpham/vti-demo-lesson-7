package vn.edu.vtiacademy.demolesson7.controller;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vn.edu.vtiacademy.demolesson7.controller.validation.AdminAccountReq;
import vn.edu.vtiacademy.demolesson7.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountReq accountReq);

    Account toAccount(AdminAccountReq accountReq);

    AccountResp toAccountResp(Account accountReq);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(AccountUpdateReq req, @MappingTarget Account account);
}
