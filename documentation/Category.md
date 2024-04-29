# Category Controller API Documentation

This document describes the API for the Category Controller.

## Table of Contents
1. [Create Category](#create-category)
2. [Update Category](#update-category)
3. [Delete Category](#delete-category)
4. [Get All Categories](#get-all-categories)
5. [Get Category by UUID](#get-category-by-uuid)

## Create Category

Description: Create a new category in the database.

Request: POST

URL: /categories/create

Body:
```json
{
    "name": "Category Name",
    "description": "Category Description"
}
```
Response:
- Status code: 201 CREATED on successful creation.
- Response body: JSON object containing the saved CategoryDto with details.


## Update Category

Description: Update a category in the database. \
Request: PUT \
URL: /categories/update/{categoryUuid} \
Path Parameters: categoryUuid \
Body:
```json
{
    "name": "Updated Category Name",
    "description": "Updated Category Description"
}
```
Response:
- Status code: 200 OK on successful update.
- Response body: JSON object containing the updated CategoryDto with details.


## Delete Category

Description: Delete a category from the database. \
Request: DELETE \
URL: /categories/delete/{categoryId} \
Path Parameters: categoryId \
Response:
- Status code: 200 OK on successful deletion.
- Response body: JSON string indicating successful deletion ("Category successfully deleted").

## Get All Categories

Description: Get all categories from the database. \
Request: GET \
URL: /categories/all \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all CategoryDto objects.

## Get Category by UUID

Description: Get a category by its categoryUuid. \
Request: GET \
URL: /categories/{categoryUuid} \
Path Parameters: categoryUuid \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON object representing the retrieved CategoryDto with details.

