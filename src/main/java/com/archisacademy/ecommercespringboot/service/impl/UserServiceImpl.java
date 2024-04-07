package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.exceptions.UserNotFoundException;
import com.archisacademy.ecommercespringboot.mapper.*;
import com.archisacademy.ecommercespringboot.model.*;
import com.archisacademy.ecommercespringboot.repository.CartRepository;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.repository.WishlistRepository;
import com.archisacademy.ecommercespringboot.service.CartService;
import com.archisacademy.ecommercespringboot.service.OrderService;
import com.archisacademy.ecommercespringboot.service.UserService;
import com.archisacademy.ecommercespringboot.service.WishlistService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final OrderService orderService;
    private final WishlistRepository wishlistRepository;
    private final WishlistService wishlistService;
    private final WishlistMapper wishlistMapper;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;


    public UserServiceImpl(UserRepository userRepository, ProductMapper productMapper, OrderService orderService, WishlistRepository wishlistRepository, WishlistService wishlistService, WishlistMapper wishlistMapper, ProductRepository productRepository, CartMapper cartMapper, CartRepository cartRepository, CartService cartService, UserMapper userMapper, OrderMapper orderMapper) {
        this.userRepository = userRepository;
        this.productMapper = productMapper;
        this.orderService = orderService;
        this.wishlistRepository = wishlistRepository;
        this.wishlistService = wishlistService;
        this.wishlistMapper = wishlistMapper;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDtoList(users);
    }

    @Override
    public UserDto getUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(String uuid, UserDto updatedUserDto) {

        Optional<User> optionalUser = userRepository.findByUuid(uuid);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }
        optionalUser.ifPresent(user -> {
            user.setUserName(updatedUserDto.getUserName());
            user.setEmail(updatedUserDto.getEmail());
            user.setPassword(updatedUserDto.getPassword());
            user.setTelephone(updatedUserDto.getTelephone());
            user.setAddress(updatedUserDto.getAddress());
            user.setUserRole(UserRole.valueOf(updatedUserDto.getUserRole().toString()));
            user.setCreatedAt(updatedUserDto.getCreatedAt());
            user.setUpdatedAt(updatedUserDto.getUpdatedAt());
            userRepository.save(user);

        });
    }

    @Override
    @Transactional
    public void deleteUser(String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException("User could be not delete"));
        userRepository.delete(user);
    }

    @Override
    public void updateWishlistForUser(String userUuid, List<String> productUuids) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user with UUID: " + userUuid));

        List<Product> products = new ArrayList<>();
        for (String productUuid : productUuids) {
            Optional<Product> optionalProduct = productRepository.findByUuid(productUuid);
            if (optionalProduct.isEmpty()) {
                continue;
            }
            Product product = optionalProduct.get();
            products.add(product);
        }
        wishlist.setProducts(products);

        wishlistRepository.save(wishlist);
    }

    @Override
    public List<ProductDto> getAllProductsByUserUuid(String userUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Product> products = user.getProductList();

        return productMapper.toProductDtoList(products);
    }

    @Override
    public WishlistResponse getWishlistByUserUUID(String userUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user with UUID: " + userUuid));

        return wishlistMapper.convertToResponse(wishlist);

    }

    @Override
    public List<OrderDto> getUserOrders(String userUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = user.getOrderList();

        return orders.stream()
                .map(orderMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartDto getUserCardDetailsWithUserUUID(String userUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with UUID: " + userUuid));

        return cartMapper.convertToDto(cart);
    }

    @Override
    @Transactional
    public void deleteCardDetailsWithUserUUID(String userUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with UUID: " + userUuid));

        cartRepository.delete(cart);
    }

    @Override
    public CartDto getUserCartByUserUUID(String userUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with UUID: " + userUuid));

        return cartMapper.convertToDto(cart);
    }

    @Override
    @Transactional
    public void updateUserCartWithUserUUID(String userUuid, CartDto updatedCartDto) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartDto currentCartDto = cartService.getCartByUserUuid(userUuid);

        cartService.updateCart(updatedCartDto, currentCartDto.getUuid());

    }
}
