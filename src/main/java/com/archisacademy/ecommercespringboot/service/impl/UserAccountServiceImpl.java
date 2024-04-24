package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.UserAccountDto;
import com.archisacademy.ecommercespringboot.mapper.UserAccountMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.model.UserAccount;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.UserAccountRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.UserAccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserAccountMapper userAccountMapper;


    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, ProductRepository productRepository, UserRepository userRepository, UserAccountMapper userAccountMapper) {
        this.userAccountRepository = userAccountRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userAccountMapper = userAccountMapper;
    }

    @Override
    @Transactional
    public String createUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUuid(UUID.randomUUID().toString());
        userAccount.setPrice(userAccountDto.getPrice());

        List<String> productUuids = Arrays.asList(userAccountDto.getProductUuids());
        List<Product> products = productRepository.findByUuidIn(productUuids);
        userAccount.setProducts(products);

        Optional<User> userOptional = userRepository.findByUuid(userAccountDto.getUserUuid());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with UUID: " + userAccountDto.getUserUuid());
        }
        User user = userOptional.get();

        user.setUserAccount(userAccount);

        userAccountRepository.save(userAccount);

        return "User account created successfully";
    }


    @Override
    public UserAccountDto getUserAccountByUuid(String uuid) {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findByUuid(uuid);
        if (userAccountOptional.isPresent()) {
            UserAccount userAccount = userAccountOptional.get();
            return userAccountMapper.toUserAccountDto(userAccount);
        } else {
            throw new RuntimeException("User account not found with ID: " + uuid);
        }
    }
}
