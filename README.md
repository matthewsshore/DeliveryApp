# DeliveryApp
Code for Delivery Mobile App

Objective:
Create "open source" delivery system where customers submit orders with the amount they are willing to pay, and drivers accept or
negotiate a price to pick up that order.

Main Paths:
/app/src/main/java/com/example/skantoro/deliveryapp_testing/ : UI and Backend Testing Environmen

/delivery_backend_testing/src/main/java/com/example/skantoro/myapplication/backend/ : Backend (Cloud SQL calls)

UI + Backend Testing Environment:
/MainActivity.java : Using this as the backend testing page.  Created the AsyncTasks that call the api. Created dummy values for API inputs.

Backend:
MyEndpoint: Contains all the functions necessary for SQL calls

Classes:
1) User
    * UserID
    * FirstName
    * LastName
    * MobileNumber
    * Role (defining customer or driver preference)
    * Email

2) Address
    * AddressID
    * Street
    * City
    * State
    * Zip
    * Lat (latitude, will be used for adding markers to map)
    * Longitude (will be used for adding markers to map)
    * UserID

3) Order
    * OrderID
    * AddressID
    * OrderName
    * Status
    * EstPrice
    * Bid
    * UserID

4) Item
    * ItemID
    * OrderID
    * ItemName
    * PictureLocation (where the image is stored, Cloud Storage)


SQL Tables:
 1) User
     * UserID (PK, AI)
     * FirstName
     * LastName
     * MobileNumber
     * Role (defining customer or driver preference)
     * Email

 2) Address
     * AddressID (PK, AI)
     * Street
     * City
     * State
     * Zip
     * Lat (latitude, will be used for adding markers to map)
     * Longitude (will be used for adding markers to map)
     * UserID (FK, references User table)

 3) Order
     * OrderID (PK, AI)
     * AddressID (FK, references Address table)
     * OrderName
     * Status
     * EstPrice
     * Bid
     * IdUser (FK, references Users)

 4) Item
     * ItemID (PK, AI)
     * OrderID (FK, references Order table)
     * ItemName
     * PictureLocation (where the image is stored, Cloud Storage)