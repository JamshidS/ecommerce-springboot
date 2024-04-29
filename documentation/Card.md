# Card Controller API Documentation
This document describes the API for the Card Controller.

## Table of Contents
1. [Save Cart](#save-cart)
2. [Update Cart](#update-cart)
3. [Delete Cart](#delete-cart)
4. [Get Cart CartUuid](#get-cart-cartUuid)
5. [Get Cart UserUuid](#get-cart-user)

## Save Cart
Description: Save a new card to the database. \
Request: POST \
URL: /cart/save \
Body:
```json
{
    "userUuid": "1234567890123456",
    "promotionCode": "1234567890123456",
  "productUuids": ["product_uuid_1", "product_uuid_2", "product_uuid_3"]
}
```
Response:
- Status code: 201 CREATED on successful creation.
- Response body: JSON object containing the saved CartDto with details.


## Update Cart

Description: Update a card in the database. \
Request: PUT \
URL: /cart/update/{cartUuid} \
Path Parameters: cartUuid \
Body:
```json
{
  "promotionCode": "1234567890123456",
  "productUuids": ["product_uuid_1", "product_uuid_2", "product_uuid_3"]
}
```
Response:
- Status code: 200 OK on successful update.
- Response body: JSON object containing the updated CartDto with details.

## Delete Cart

Description: Delete a card from the database. \
Request: DELETE \
URL: /cart/delete/{cartUuid} \
Path Parameters: cartUuid \
Response:
- Status code: 200 OK on successful deletion.
- Response body: JSON string indicating successful deletion ("Cart successfully deleted").

## Get Cart CartUuid

Description: Get a card by its cartUuid. \
Request: GET \
URL: /cart/{cartUuid} \
Path Parameters: cartUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON object representing the retrieved CartDto with details.

## Get Cart User

Description: Get a card by its userUuid. \
Request: GET \
URL: /cart/user/{userUuid} \
Path Parameters: userUuid \
Response:
- Status code: 200 OK if a cart exists for the user.
- Response body: JSON object representing the retrieved CartDto with details.
