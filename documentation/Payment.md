# Payment Controller API Documentation

This document describes the API for the Payment Controller.

## Table of Contents
1. [Save Payment](#save-payment)
2. [Update Payment](#update-payment-userUuid)
3. [Delete Payment](#delete-payment)
4. [Get Payment by UUID](#get-payment-by-uuid)
5. [Get Payment by User UUID](#get-payment-by-user-uuid)
6. [Return Payment](#return-payment)

## Save Payment

Description: Save a new payment to the database. \
Request: POST \
URL: /payment/save \
Body:
```json
{
    "name": "Tevfik Guzel",
    "cardNumber": "1234567890123456",
    "expirationDate": "12/23",
    "cvv": "123",
    "userUuid": "123",
    "orderUuid": "123",
    "amount": 100.00
}
```
Response:
- Status code: 201 CREATED on successful creation.
- Response body: JSON object containing the saved PaymentDto with details.


## Update Payment

Description: Update a payment in the database. \
Request: PUT \
URL: /payment/update/{paymentUuid} \
Path Parameters: paymentUuid \
Body:
```json
{
    "name": "Tevfik Guzel",
    "cardNumber": "1234567890123456",
    "expirationDate": "12/23",
    "cvv": "123",
    "userUuid": "123",
    "orderUuid": "123",
    "amount": 100.00
}
```

Response:
- Status code: 200 OK on successful update.
- Response body: JSON object containing the updated PaymentDto with details.


## Delete Payment

Description: Delete a payment from the database. \
Request: DELETE \
URL: /payment/delete/{userUuid} \
Path Parameters: userUuid \
Response:
- Status code: 200 OK on successful deletion.
- Response body: JSON string indicating successful deletion ("Payment successfully deleted").

## Get Payment by UUID

Description: Get a payment by its paymentUuid. \
Request: GET \
URL: /payment/{paymentUuid} \
Path Parameters: paymentUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON object representing the retrieved PaymentDto with details.

## Get Payment by User UUID

Description: Get a payment by its userUuid. \
Request: GET \
URL: /payment/user/{userUuid} \
Path Parameters: userUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON object representing the retrieved PaymentDto with details.

## Return Payment

Description: Return a payment. \
Request: PUT \
URL: /payment/return/{paymentUuid} \
Path Parameters: paymentUuid \
Body:
```json
{
    "amount": 100.00
}
```
Response:
- Status code: 200 OK on successful return.
- Response body: JSON object containing the returned PaymentDto with details.



