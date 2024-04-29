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
        userAccountDto.setCreatedAt(userAccount.getCreatedAt());
        userAccountDto.setOrderNumber(userAccount.getOrderNumber());
        userAccountDto.setOrderStatus(userAccount.getOrderStatus());
        userAccountDto.setRefundAmount(userAccount.getRefundAmount());
        userAccountDto.setRefundDate(userAccount.getRefundDate());
        userAccountDto.setUserUuid(userAccount.getUser().getUuid());
        return userAccountDto;
    }

    public UserAccount toUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUuid(userAccountDto.getUuid());
        userAccount.setPrice(userAccountDto.getPrice());
        userAccount.setCreatedAt(userAccountDto.getCreatedAt());
        userAccount.setOrderNumber(userAccountDto.getOrderNumber());
        userAccount.setOrderStatus(userAccountDto.getOrderStatus());
        userAccount.setRefundAmount(userAccountDto.getRefundAmount());
        userAccount.setRefundDate(userAccountDto.getRefundDate());

        return userAccount;
    }
}
