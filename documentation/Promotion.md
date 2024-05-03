# Promotion Controller API Documentation

This document outlines the API for the Promotion Controller.

## Table of Contents

1. [Save Promotion](#save-promotion)
2. [Update Promotion](#update-promotion)
3. [Delete Promotion](#delete-promotion)
4. [Check Promotion Validity](#check-promotion-validity)
5. [Get All Promotions](#get-all-promotions)
6. [Get Promotion by UUID](#get-promotion-by-uuid)
7. [Get Promotions by Product UUID](#get-promotions-by-product-uuid)

## Save Promotion

**Description**: Save a new promotion to the database.

- **Request**: POST
- **URL**: /promotions/save
- **Body**:

```json
{
  "name": "Promotion Name",
  "description": "Promotion Description",
  "discount": 0.1,
  "code": "PROMO123",
  "productUuid": [
    "product_uuid_1",
    "product_uuid_2"
  ],
  "fullName": "Full Promotion Name",
  "daysToAdd": 30,
  "startDate": "2024-05-03T00:00:00",
  "endDate": "2024-06-02T00:00:00"
}
```

Response:

```json
{
  "status": 201,
  "message": "Promotion successfully created"
}

```

## Update Promotion

**Description**: Update an existing promotion in the database.

- **Request**: PUT
- **URL**: /promotions/update
- **Body**:

```json
{
    "uuid": "promotion_uuid",
    "name": "Updated Promotion Name",
    "description": "Updated Promotion Description",
    "discount": 0.2,
    "code": "UPDATED123",
    "productUuid": ["product_uuid_1", "product_uuid_2"],
    "fullName": "Updated Full Promotion Name",
    "daysToAdd": 45,
    "startDate": "2024-05-03T00:00:00",
    "endDate": "2024-07-02T00:00:00"
}
```
Response:

```json
{
  "status": 200,
  "message": "Promotion successfully updated"
}
```

## Delete Promotion
Description: Delete a promotion from the database.

**Description**: Update an existing promotion in the database.

- **Request**: DELETE
- **URL**: /promotions/delete/{promotionUuid}
- **Body**: promotionUuid

Response:
```json
{
"status": 200,
"message": "Promotion successfully deleted"
}
```
## Check Promotion Validity

**Description**: Check if a promotion is valid.

- **Request**: GET
- **URL**: /promotions/validate/{promotionUuid}
- **Body**: promotionUuid

Response:
```json
{
  "valid": true
}
```

## Get All Promotions
**Description**: Retrieve all promotions.

* Request: GET
* URL: /promotions/all

Response:

```json
[
  {
    "uuid": "promotion_uuid_1",
    "name": "Promotion 1",
    "description": "Promotion Description 1",
    "discount": 0.1,
    "code": "PROMO123",
    "productUuid": ["product_uuid_1", "product_uuid_2"],
    "fullName": "Full Promotion Name 1",
    "daysToAdd": 30,
    "startDate": "2024-05-03T00:00:00",
    "endDate": "2024-06-02T00:00:00"
  },
  {
    "uuid": "promotion_uuid_2",
    "name": "Promotion 2",
    "description": "Promotion Description 2",
    "discount": 0.2,
    "code": "PROMO456",
    "productUuid": ["product_uuid_3"],
    "fullName": "Full Promotion Name 2",
    "daysToAdd": 45,
    "startDate": "2024-05-03T00:00:00",
    "endDate": "2024-07-02T00:00:00"
  }
]
```

## Get Promotion by UUID
**Description**: Retrieve a promotion by its UUID.

- **Request**: GET
- **URL**: /promotions/{promotionUuid}
- **Path Parameters**: promotionUuid

Response:

```json
{
  "uuid": "promotion_uuid",
  "name": "Promotion Name",
  "description": "Promotion Description",
  "discount": 0.1,
  "code": "PROMO123",
  "productUuid": ["product_uuid_1", "product_uuid_2"],
  "fullName": "Full Promotion Name",
  "daysToAdd": 30,
  "startDate": "2024-05-03T00:00:00",
  "endDate": "2024-06-02T00:00:00"
}
```
## Get Promotions by Product UUID

**Description**: Retrieve all promotions associated with a product UUID.

- **Request**: GET
- **URL**: /promotions/product/{productUuid}
- **Path Parameters**: productUuid

Response:
```json
[
    {
        "uuid": "promotion_uuid_1",
        "name": "Promotion 1",
        "description": "Promotion Description 1",
        "discount": 0.1,
        "code": "PROMO123",
        "productUuid": ["product_uuid_1", "product_uuid_2"],
        "fullName": "Full Promotion Name 1",
        "daysToAdd": 30,
        "startDate": "2024-05-03T00:00:00",
        "endDate": "2024-06-02T00:00:00"
    },
    {
        "uuid": "promotion_uuid_2",
        "name": "Promotion 2",
        "description": "Promotion Description 2",
        "discount": 0.2,
        "code": "PROMO456",
        "productUuid": ["product_uuid_3"],
        "fullName": "Full Promotion Name 2",
        "daysToAdd": 45,
        "startDate": "2024-05-03T00:00:00",
        "endDate": "2024-07-02T00:00:00"
    }
]