# Product Controller API Documentation

This document describes the API for the Product Controller.

## Table of Contents
1. [Create Product](#create-product)
2. [Update Product](#update-product)
3. [Delete Product](#delete-product)
4. [Get All Products](#get-all-products)
5. [Get Product by UUID](#get-product-by-uuid)
6. [Get Product by Category UUID](#get-product-by-category-uuid)
7. [Get Product By user UUID](#get-product-by-user-uuid)
8. [Get Product By Promotion UUID](#get-product-by-promotion-uuid)
9. [Get Product By Price](#get-product-by-price)
10. [Get Product By Price Range](#get-product-by-price-range)

## Create Product

Description: Create a new product in the database. \
Request: POST \
URL: /products/create \
Body:
```json
{
    "name": "Product Name",
    "description": "Product Description",
    "price": 100.00,
    "categoryUuid": "123",
    "userUuid": "123",
    "promotionUuid": "123"
}
```
Response:
- Status code: 201 CREATED on successful creation.
- Response body: JSON object containing the saved ProductDto with details.


## Update Product

Description: Update a product in the database. \
Request: PUT \
URL: /products/update/{productUuid} \
Path Parameters: productUuid \
Body:
```json
{
    "name": "Updated Product Name",
    "description": "Updated Product Description",
    "price": 200.00,
    "categoryUuid": "123",
    "userUuid": "123",
    "promotionUuid": "123"
}
```

Response:
- Status code: 200 OK on successful update.
- Response body: JSON object containing the updated ProductDto with details.


## Delete Product

Description: Delete a product from the database. \
Request: DELETE \
URL: /products/delete/{productId} \
Path Parameters: productId \
Response:
- Status code: 200 OK on successful deletion.
- Response body: JSON string indicating successful deletion ("Product successfully deleted").

## Get All Products

Description: Get all products from the database. \
Request: GET \
URL: /products \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all ProductDto objects.

## Get Product by UUID

Description: Get a product by its productUuid. \
Request: GET \
URL: /products/{productUuid} \
Path Parameters: productUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON object containing the ProductDto with details.

## Get Product by Category UUID

Description: Get all products by their categoryUuid. \
Request: GET \
URL: /products/category/{categoryUuid} \
Path Parameters: categoryUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all ProductDto objects with the specified categoryUuid.

## Get Product By user UUID

Description: Get all products by their userUuid. \
Request: GET \
URL: /products/user/{userUuid} \
Path Parameters: userUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all ProductDto objects with the specified userUuid.

## Get Product By Promotion UUID

Description: Get all products by their promotionUuid. \
Request: GET \
URL: /products/promotion/{promotionUuid} \
Path Parameters: promotionUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all ProductDto objects with the specified promotionUuid.

## Get Product By Price

Description: Get all products by their price. \
Request: GET \
URL: /products/price/{price} \
Path Parameters: price \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all ProductDto objects with the specified price.

## Get Product By Price Range

Description: Get all products by their price range. \
Request: GET \
URL: /products/price/{minPrice}/{maxPrice} \
Path Parameters: minPrice, maxPrice \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all ProductDto objects within the specified price range.

