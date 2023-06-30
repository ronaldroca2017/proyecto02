package com.nttdata.accountmicroservice.mapper;


import com.nttdata.accountmicroservice.entity.Account;
import com.openapi.gen.springboot.dto.AccountDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements EntityMapper<AccountDTO, Account> {

    @Override
    public Account toEntity(AccountDTO dto) {
        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public AccountDTO toDto(Account entity) {
        AccountDTO dto = new AccountDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
