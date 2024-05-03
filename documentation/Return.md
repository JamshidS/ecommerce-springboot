# Return Controller API Documentation

This document outlines the API for the Return Controller.

## Table of Contents

1. [Create Return](#create-return)
2. [Update Return](#update-return)
3. [Get Return by ID](#get-return-by-id)
4. [Get All Returns](#get-all-returns)
5. [Delete Return](#delete-return)

## Create Return

**Description**: Create a new return associated with a payment.

- **Request**: POST
- **URL**: /returns/create/{paymentUuid}
- **Path Parameters**: paymentUuid
- **Body**:
```json
{
    "address": "Return Address",
    "reason": "Return Reason",
    "returnDate": "2024-05-03T00:00:00",
    "userUuid": "user_uuid",
    "productUuid": "product_uuid",
    "cartId": 1,
    "orderId": 1
}
```
Response:

* Status code: 201 CREATED on successful creation.
* Response body: String indicating the success message.


## Update Return
**Description**: Update an existing return.

* **Request**: PUT
* **URL**: /returns/update
* **Body**:

```json
{
    "address": "Updated Return Address",
    "reason": "Updated Return Reason",
    "returnDate": "2024-05-03T00:00:00",
    "userUuid": "updated_user_uuid",
    "productUuid": "updated_product_uuid",
    "cartId": 2,
    "orderId": 2
}
```
Response:

* Status code: 200 OK on successful update.
* Response body: String indicating the success message.

## Get Return by ID
**Description**: Retrieve a return by its ID.

* **Request**: GET
* **URL**: /returns/{returnId}
* **Path Parameters**: returnId


* **Response**:
```json
{
"address": "Return Address",
"reason": "Return Reason",
"returnDate": "2024-05-03T00:00:00",
"userUuid": "user_uuid",
"productUuid": "product_uuid",
"cartId": 1,
"orderId": 1
}
```

* Status code: 200 OK on successful retrieval.
* Response body: JSON object representing the retrieved ReturnDto with details.

## Get All Returns
**Description**: Retrieve all returns.

**Request**: GET
**URL**: /returns

Response:
```json
[
    {
        "address": "Return Address 1",
        "reason": "Return Reason 1",
        "returnDate": "2024-05-03T00:00:00",
        "userUuid": "user_uuid_1",
        "productUuid": "product_uuid_1",
        "cartId": 1,
        "orderId": 1
    },
    {
        "address": "Return Address 2",
        "reason": "Return Reason 2",
        "returnDate": "2024-05-03T00:00:00",
        "userUuid": "user_uuid_2",
        "productUuid": "product_uuid_2",
        "cartId": 2,
        "orderId": 2
    }
]
```
* Status code: 200 OK on successful retrieval.
* Response body: List of ReturnDto containing all returns.


## Delete Return
**Description**: Delete a return by its ID.

* **Request**: DELETE
* **URL**: /returns/delete/{returnId}
* **Path Parameters**: returnId


**Response**:
* Status code: 200 OK on successful deletion.
* Response body: No content.































