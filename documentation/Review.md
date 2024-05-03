# Review Controller API Documentation

This document outlines the API for the Review Controller.

## Table of Contents

1. [Save Review](#save-review)
2. [Get Review by User and Product](#get-review-by-user-and-product)
3. [Get Reviews by Product](#get-reviews-by-product)
4. [Update Review by User and Product](#update-review-by-user-and-product)
5. [Delete Review by User and Product](#delete-review-by-user-and-product)
6. [Delete Reviews by Review UUID](#delete-reviews-by-review-uuid)
7. [Get All Approved Reviews by Product](#get-all-approved-reviews-by-product)
8. [Approve Review](#approve-review)

## Save Review

**Description**: Save a new review.

- **Request**: POST
- **URL**: /reviews/save
- **Body**:
```json
{
    "uuid": "review_uuid",
    "rating": 4,
    "comment": "This product is great!",
    "createdAt": "2024-05-03T12:00:00",
    "userUuid": "user_uuid",
    "productUuid": "product_uuid",
    "isApproved": true
}
```
Response:
```json
{
    "uuid": "review_uuid",
    "rating": 4,
    "comment": "This product is great!",
    "createdAt": "2024-05-03T12:00:00",
    "userUuid": "user_uuid",
    "productUuid": "product_uuid",
    "isApproved": true
}
```
## Get Review by User and Product
**Description**: Retrieve a review by user UUID and product UUID.

* **Request**: GET
* **URL**: /reviews
* **Query Parameters**:
* userUuid
* productUuid

Response:
```json
{
    "uuid": "review_uuid",
    "rating": 4,
    "comment": "This product is great!",
    "createdAt": "2024-05-03T12:00:00",
    "userUuid": "user_uuid",
    "productUuid": "product_uuid",
    "isApproved": true
}
```
## Get Reviews by Product
**Description**: Retrieve all reviews for a product.

* **Request**: GET
* **URL**: /reviews/product
* **Query Parameters:**
* productUuid

Response:
```json
[
    {
        "uuid": "review_uuid_1",
        "rating": 4,
        "comment": "This product is great!",
        "createdAt": "2024-05-03T12:00:00",
        "userUuid": "user_uuid_1",
        "productUuid": "product_uuid",
        "isApproved": true
    },
    {
        "uuid": "review_uuid_2",
        "rating": 5,
        "comment": "Excellent product!",
        "createdAt": "2024-05-04T10:00:00",
        "userUuid": "user_uuid_2",
        "productUuid": "product_uuid",
        "isApproved": true
    }
]
```
Update Review by User and Product
Description: Update a review by user UUID and product UUID.

* **Request**: PUT
* **URL**: /reviews/update
* **Query Parameters:**
* userUuid
* productUuid
* Body:
```json
{
    "rating": 5,
    "comment": "Updated review comment",
    "isApproved": true
}
```
Response:
```json
"Review successfully updated"
```

## Delete Review by User and Product
**Description**: Delete a review by user UUID and product UUID.

* **Request**: DELETE
* **URL**: /reviews/delete
* **Query Parameters:**
* userUuid
* productUuid

Response:
```json
"Review successfully deleted"
```
## Delete Reviews by Review UUID
**Description**: Delete a review by its UUID.

* **Request**: DELETE
* **URL**: /reviews/delete/{reviewUuid}

Response:
```json
"Review successfully deleted"
```
## Get All Approved Reviews by Product
**Description**: Retrieve all approved reviews for a product.

* **Request**: GET
* **URL**: /reviews/approved
* **Query Parameters:**
* productUuid

Response:
````json
[
    {
        "uuid": "review_uuid_1",
        "rating": 4,
        "comment": "This product is great!",
        "createdAt": "2024-05-03T12:00:00",
        "userUuid": "user_uuid_1",
        "productUuid": "product_uuid",
        "isApproved": true
    },
    {
        "uuid": "review_uuid_2",
        "rating": 5,
        "comment": "Excellent product!",
        "createdAt": "2024-05-04T10:00:00",
        "userUuid": "user_uuid_2",
        "productUuid": "product_uuid",
        "isApproved": true
    }
]
````
## Approve Review
**Description**: Approve a review by its UUID.

* **Request**: PUT
* **URL**: /reviews/approve
* **Query Parameters:**
* reviewUuid

Response:
```json
"Review successfully approved"
```