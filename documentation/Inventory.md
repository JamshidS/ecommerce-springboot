# Inventory Controller API Documentation

This document describes the API for the Inventory Controller.

## Table of Contents
1. [Create Inventory](#create-inventory)
2. [Update Inventory](#update-inventory)
3. [Delete Inventory](#delete-inventory)
4. [Get All Inventories](#get-all-inventories)
5. [Get Inventory by ID](#get-inventory-by-id)

## Create Inventory

Description: Create a new inventory in the database. \
Request: POST \
URL: /inventory/create \
Body:
```json
{
    "quantity": 10,
    "referenceCode": "1234567"
}
```
Response:
- Status code: 201 CREATED on successful creation.
- Response body: JSON object containing the saved InventoryDto with details.


## Update Inventory

Description: Update an inventory in the database. \
Request: PUT \
URL: /inventory/update/{inventoryId} \
Path Parameters: inventoryId \
Body:
```json
{
    "quantity": "product_uuid",
    "referenceCode":"213456"
}
```
Response:
- Status code: 200 OK on successful update.
- Response body: JSON object containing the updated InventoryDto with details.


## Delete Inventory

Description: Delete an inventory from the database. \
Request: DELETE \
URL: /inventory/delete/{inventoryId} \
Path Parameters: inventoryId \
Response:
- Status code: 200 OK on successful deletion.
- Response body: JSON string indicating successful deletion ("Inventory successfully deleted").

## Get All Inventories

Description: Get all inventories from the database. \
Request: GET \
URL: /inventory/all \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON array containing all InventoryDto objects.

## Get Inventory by ID

Description: Get an inventory by its inventoryId. \
Request: GET \
URL: /inventory/{inventoryId} \
Path Parameters: inventoryId \
Response:
- Status code: 200 OK on successful retrieval.
- Response body: JSON object representing the retrieved InventoryDto with details.
