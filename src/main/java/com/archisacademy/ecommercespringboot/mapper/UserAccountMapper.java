package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.UserAccountDto;
import com.archisacademy.ecommercespringboot.model.UserAccount;

public interface UserAccountMapper {
    UserAccountDto toUserAccountDto(UserAccount userAccount);

    UserAccount toUserAccount(UserAccountDto userAccountDto);
}
