package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.UserAccountDto;

public interface UserAccountService {
    String createUserAccount(UserAccountDto userAccountDto);

    UserAccountDto getUserAccountById(Long id);
}
