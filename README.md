# Data-Centric-RAD-Project
This is my 3rd year project for my Data Centric RAD module in college. The brief for this project is to write a JSF/JDBC dynamic web application that queries and updates a MySQL database.

#### Module
This was a semester long module which covered
+ Relational Databases (MySQL)
+ Java Database Connectivity (JDBC)
+ Java Server Faces (JSF)

This project was worth 50% of the module.

#### Setting up the database
The database used for this project is provided. To import the database use the following command:

```
mysql -u username -p password < "Full Path\GarageDB.sql"
```

The server must be running to use the application. I used WAMP for this project. Depending on the server of Tomcat you're using you may have to edit the VM arguments. Right click on Servers folder go to Run As->Run Configurations. Navigate to the Arguments tab and add the following line to the textbox under VM arguments.

```
-Dorg.apache.el.parser.COERCE_TO_ZERO=false
```

This will make Float objects default to null rather than 0 which is necessary for the search to function correctly.

#### Architecture
JSF follows the MVC design pattern in order to seperate concerns. Therefore, the project is split up into views, controllers, bean classes and the DAO (Database Access Object).

##### Views
Views refer to the GUI aspect of the application. There are twelve views in total in this project they are:
+ Add Manufacturer
+ Add Model
+ Add Vehicle
+ Find Vehicles
+ Index
+ List Manufacturers
+ List Models
+ List Vehicles
+ Search Results
+ Update Manufacturers
+ Vehicle Details

##### Controllers
The controllers link the views to the DAO. They contain data and methods that can be accessed from the views and can call methods in the DAO. Each controller should be responsible for one thing. There are three controllers in this project. One for vehicles, models and manufacturers.

##### Bean Classes
Bean classes refer to the classes that only store data and have no functionality. They store data retrieved from the database and are generally stored in the controllers so that the views can access them. There are three bean class, a vehicle, model and manufacturer class

##### DAO
The DAO class is the only class that should ever interact with the database. The purpose of a DAO is to ensure independance from any database. If the database changes the DAO class is the only class that will change.