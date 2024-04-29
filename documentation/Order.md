# Order Controller API Documentation

This document describes the API for the Order Controller.

## Table of Contents
1. [Create Order](#create-order)
2. [Update Order](#update-order)
3. [Delete Order](#delete-order)
4. [Get All Orders](#get-all-orders)
5. [Get Order by UUID](#get-order-by-uuid)
6. [Get Order by User UUID](#get-order-by-user-uuid)

## Create Order

Description: Create a new order in the database. \
Request: POST \
URL: /order/create \
Body:
```json
{
    "userUuid": "1234567890123456",
    "productUuids": ["product_uuid_1", "product_uuid_2", "product_uuid_3"]
}
```
Response:
- Status code: 201 CREATED on successful creation.
- Response body: JSON object containing the saved OrderDto with details.


## Update Order

Description: Update an order in the database. \
Request: PUT \
URL: /order/update/{orderUuid} \
Path Parameters: orderUuid \
Body:
```json
{
    "productUuids": ["product_uuid_1", "product_uuid_2", "product_uuid_3"]
}
```
Response:
- Status code: 200 OK on successful update.
- Response body: JSON object containing the updated OrderDto with details.


## Delete Order

Description: Delete an order from the database. \
Request: DELETE \
URL: /order/delete/{orderUuid} \
Path Parameters: orderUuid \
Response:
- Status code: 200 OK on successful deletion.
- Response body: JSON string indicating successful deletion ("Order successfully deleted").

## Get All Orders

Description: Get all orders from the database. \
Request: GET \
URL: /order \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all OrderDto objects.

## Get Order by UUID

Description: Get an order by its orderUuid. \
Request: GET \
URL: /order/{orderUuid} \
Path Parameters: orderUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON object representing the retrieved OrderDto with details.

## Get Order by User UUID

Description: Get all orders for a user by userUuid. \
Request: GET \
URL: /order/user/{userUuid} \
Path Parameters: userUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all OrderDto objects for the user.


