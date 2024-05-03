# User Controller API Documentation

This document outlines the API for the User Controller.

## Table of Contents

1. [Get All Users](#get-all-users)
2. [Get User by UUID](#get-user-by-uuid)
3. [Save User](#save-user)
4. [Update User](#update-user)
5. [Delete User](#delete-user)
6. [Get Wishlist by User UUID](#get-wishlist-by-user-uuid)
7. [Update Wishlist for User](#update-wishlist-for-user)
8. [Get All Products by User UUID](#get-all-products-by-user-uuid)
9. [Get User Orders](#get-user-orders)
10. [Get User Cart Details with User UUID](#get-user-cart-details-with-user-uuid)
11. [Get User Cart by User UUID](#get-user-cart-by-user-uuid)
12. [Delete Cart Details with User UUID](#delete-cart-details-with-user-uuid)
13. [Update User Cart with User UUID](#update-user-cart-with-user-uuid)

## Get All Users

**Description**: Retrieve all users.

- **Request**: GET
- **URL**: /users
  Response:
```json
[
    {
        "uuid": "user_uuid_1",
        "userName": "user1",
        "email": "user1@example.com",
        "password": "user1_password",
        "telephone": "1234567890",
        "address": "Address 1",
        "userRole": "USER",
        "createdAt": "2024-05-03T12:00:00",
        "updatedAt": "2024-05-03T12:00:00"
    },
    {
        "uuid": "user_uuid_2",
        "userName": "user2",
        "email": "user2@example.com",
        "password": "user2_password",
        "telephone": "0987654321",
        "address": "Address 2",
        "userRole": "ADMIN",
        "createdAt": "2024-05-04T10:00:00",
        "updatedAt": "2024-05-04T10:00:00"
    }
]
```
## Get User by UUID
**Description**: Retrieve a user by UUID.

* **Request**: GET
* **URL**: /users/{uuid}
* **Path Parameters**: uuid (User UUID)

Response:

```json
{
    "uuid": "user_uuid",
    "userName": "username",
    "email": "user@example.com",
    "password": "user_password",
    "telephone": "1234567890",
    "address": "Address",
    "userRole": "USER",
    "createdAt": "2024-05-03T12:00:00",
    "updatedAt": "2024-05-03T12:00:00"
}
```
## Save User
**Description**: Save a new user.

**Request**: POST
**URL**: /users
**Body**:
```json
{
    "userName": "username",
    "email": "user@example.com",
    "password": "user_password",
    "telephone": "1234567890",
    "address": "Address",
    "userRole": "USER"
}
```
Response:

- Status code: 201 CREATED

## Update User
**Description**: Update an existing user.

**Request**: PUT
**URL**: /users/{uuid}
**Path Parameters**: uuid (User UUID)
**Body**:

```json
{
    "userName": "updated_username",
    "email": "updated_user@example.com",
    "password": "updated_user_password",
    "telephone": "0987654321",
    "address": "Updated Address",
    "userRole": "ADMIN"
}
```
Response:

- Status code: 200 OK

## Delete User
**Description**: Delete a user by UUID.

* **Request**: DELETE
* **URL**: /users/{uuid}
* **Path Parameters:** uuid (User UUID)

Response:
- Status code: 200 OK

## Get Wishlist by User UUID
**Description**: Retrieve wishlist items by user UUID.

* **Request**: GET
* **URL**: /users/wishlist/{uuid}
* **Path Parameters:** uuid (User UUID)

Response:

```json
{
    "wishlistItems": [
        "product_uuid_1",
        "product_uuid_2"
    ]
}
```

## Update Wishlist for User
**Description**: Update wishlist items for a user.

* **Request**: PUT
* **URL**: /users/wishlist/{uuid}
* **Path Parameters**: uuid (User UUID)
* **Body**: Array of product UUIDs


```json
[
  "product_uuid_1",
  "product_uuid_2",
  "product_uuid_3"
]
```

Response:

- Status code: 200 OK

## Get All Products by User UUID
**Description**: Retrieve all products associated with a user by UUID.

* **Request**: GET
* **URL**: /users/products/{uuid}
* **Path Parameters**: uuid (User UUID)
Response:


```json
[
    {
        "uuid": "product_uuid_1",
        "name": "Product 1",
        "description": "Description 1",
        "price": 10.99
    },
    {
        "uuid": "product_uuid_2",
        "name": "Product 2",
        "description": "Description 2",
        "price": 19.99
    }
]
```

## Get User Orders
**Description**: Retrieve orders associated with a user by UUID.

* **Request**: GET
* **URL**: /users/orders/{uuid}
* **Path Parameters**: uuid (User UUID)

Response:

```json
[
    {
        "uuid": "order_uuid_1",
        "orderDate": "2024-05-03T12:00:00",
        "totalPrice": 30.98,
        "status": "PROCESSING"
    },
    {
        "uuid": "order_uuid_2",
        "orderDate": "2024-05-04T10:00:00",
        "totalPrice": 45.99,
        "status": "DELIVERED"
    }
]
```

## Get User Cart Details with User UUID
**Description**: Retrieve cart details associated with a user by UUID.

* **Request**: GET
* **URL**: /users/cart/details/{uuid}
* **Path Parameters**: uuid (User UUID)

Response:
```json
{
    "uuid": "cart_uuid",
    "orderDate": "2024-05-03",
    "userUuid": "user_uuid",
    "promotionCode": "PROMO123",
    "productUuids": ["product_uuid_1", "product_uuid_2"],
    "productDtoList": [
        {
            "uuid": "product_uuid_1",
            "name": "Product 1",
            "description": "Description 1",
            "price": 10.99
        },
        {
            "uuid": "product_uuid_2",
            "name": "Product 2",
            "description": "Description 2",
            "price": 19.99
        }
    ]
}
```
## Get User Cart by User UUID
**Description**: Retrieve cart associated with a user by UUID.

**Request**: GET
**URL**: /users/cart/{uuid}
**Path Parameters**: uuid (User UUID)
Response:
```json
{
    "uuid": "cart_uuid",
    "orderDate": "2024-05-03",
    "userUuid": "user_uuid",
    "promotionCode": "PROMO123",
    "productUuids": ["product_uuid_1", "product_uuid_2"]
}
```

## Delete Cart Details with User UUID
Description: Delete cart details associated with a user by UUID.

* **Request**: DELETE
* **URL**: /users/cart/{uuid}
* **Path Parameters**: uuid (User UUID)

Response:
- Status code: 200 OK

## Update User Cart with User UUID
**Description**: Update cart details associated with a user by UUID.

* **Request**: PUT
* **URL**: /users/cart/{uuid}
* **Path Parameters**: uuid (User UUID)
* **Body**:

```json
{
    "uuid": "cart_uuid",
    "orderDate": "2024-05-03",
    "userUuid": "user_uuid",
    "promotionCode": "PROMO123",
    "productUuids": ["product_uuid_1", "product_uuid_2", "product_uuid_3"]
}
```

Response:
- Status code: 200 OK













































