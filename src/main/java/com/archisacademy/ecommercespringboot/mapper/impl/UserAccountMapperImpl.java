package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.UserAccountDto;
import com.archisacademy.ecommercespringboot.mapper.UserAccountMapper;
import com.archisacademy.ecommercespringboot.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapperImpl implements UserAccountMapper {
    public UserAccountDto toUserAccountDto(UserAccount userAccount) {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setUuid(userAccount.getUuid());
        userAccountDto.setPrice(userAccount.getPrice());
        // Burada diğer alanları da doldurabilirsiniz, eğer gerekirse
        return userAccountDto;
    }

    public UserAccount toUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUuid(userAccountDto.getUuid());
        userAccount.setPrice(userAccountDto.getPrice());
        // Burada diğer alanları da doldurabilirsiniz, eğer gerekirse
        return userAccount;
    }
}
