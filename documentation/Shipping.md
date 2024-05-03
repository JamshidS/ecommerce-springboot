# Shipping Controller API Documentation

This document outlines the API for the Shipping Controller.

## Table of Contents

1. [Create Shipping](#create-shipping)
2. [Update Shipping](#update-shipping)
3. [Delete Shipping](#delete-shipping)
4. [Get Shipping by ID](#get-shipping-by-id)
5. [Get All Shippings](#get-all-shippings)

## Create Shipping

**Description**: Create a new shipping record.

- **Request**: POST
- **URL**: /shippings/create
- **Body**:
```json
{
    "address": "Shipping Address",
    "shippedAt": "2024-05-03T12:00:00",
    "senderUuid": "sender_uuid",
    "orderUuid": "order_uuid"
}
```
Response
:
````json
{
    "status": 201,
    "message": "Shipping successfully created"
}
````
## Update Shipping
**Description**: Update an existing shipping record.

* **Request**: PUT
* **URL**: /shippings/update/{id}
* **Path Parameters**: id (Shipping ID)
* **Body**:

```json
{
    "address": "Updated Shipping Address",
    "shippedAt": "2024-05-03T12:00:00",
    "senderUuid": "updated_sender_uuid",
    "orderUuid": "updated_order_uuid"
}
```
Response:
```json
{
    "status": 200,
    "message": "Shipping successfully updated"
}
```
## Delete Shipping
**Description**: Delete a shipping record by its ID.

* **Request**: DELETE
* **URL**: /shippings/delete/{id}
* **Path Parameters**: id (Shipping ID)

Response:
```json
{
    "status": 200,
    "message": "Shipping successfully deleted"
}
```
## Get Shipping by ID
**Description**: Retrieve a shipping record by its ID.

* **Request**: GET
* **URL**: /shippings/{id}
* **Path Parameters**: id (Shipping ID)

Response:
```json
{
    "address": "Shipping Address",
    "shippedAt": "2024-05-03T12:00:00",
    "senderUuid": "sender_uuid",
    "orderUuid": "order_uuid"
}
```
## Get All Shippings
**Description**: Retrieve all shipping records.

* **Request**: GET
* **URL**: /shippings/all

Response:
```json
[
    {
        "address": "Shipping Address 1",
        "shippedAt": "2024-05-03T12:00:00",
        "senderUuid": "sender_uuid_1",
        "orderUuid": "order_uuid_1"
    },
    {
        "address": "Shipping Address 2",
        "shippedAt": "2024-05-04T10:00:00",
        "senderUuid": "sender_uuid_2",
        "orderUuid": "order_uuid_2"
    }
]
```