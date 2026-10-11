# Hibernate Vehicle Management System

A standalone Maven-based **Vehicle Management System** developed using Core Java, Hibernate ORM, and MySQL. This project demonstrates Hibernate entity relationships, CRUD operations, transaction management, and custom exception handling.

## 🚀 Project Overview

The application manages vehicle details along with one associated insurance record and multiple service records for each vehicle. It uses Hibernate ORM to map Java entity classes to MySQL database tables.

## 🛠️ Technologies Used

- **Programming Language:** Java 17+
- **ORM Framework:** Hibernate ORM
- **Database:** MySQL
- **Build Tool:** Maven
- **Database Connectivity:** JDBC
- **Concepts:** OOP, CRUD Operations, Entity Relationships, Transactions, Custom Exceptions

## ✨ Features

- Insert a vehicle with insurance details and multiple service records.
- Retrieve a vehicle by its ID.
- Retrieve all vehicle records.
- Update vehicle details, including name, number, type, and price.
- Delete a vehicle by ID.
- Delete all vehicle records with explicit confirmation.
- Handle missing records using custom exceptions.
- Manage Hibernate sessions and transactions.
- Provide a menu-driven console interface using a `switch` statement.

## 🏗️ Project Structure

```text
VehicleManagementSystem/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── app/
│       │           ├── model/
│       │           │   ├── Vehicle.java
│       │           │   ├── Insurance.java
│       │           │   └── ServiceRecord.java
│       │           ├── config/
│       │           │   └── HibernateUtil.java
│       │           ├── servicei/
│       │           │   └── Requirements.java
│       │           ├── sericeImpl/
│       │           │   └── RequirementsImpl.java
│       │           ├── client/
│       │           │   └── Test.java
│       │           └── exception/
│       │               ├── VehicleNotFoundException.java
│       │               ├── InsuranceNotFoundException.java
│       │               ├── ServiceRecordNotFoundException.java
│       │               └── VehicleOperationException.java
│       └── resources/
│           └── hibernate.cfg.xml
├── pom.xml
└── README.md
```

## 🗃️ Entity Details

### 1. Vehicle

- `vehicleId` — Primary key
- `vehicleName` — Vehicle name or model
- `vehicleNumber` — Registration number
- `vehicleType` — Car, bike, truck, etc.
- `price` — Vehicle price
- `insurance` — Associated insurance record
- `serviceRecords` — Collection of service records

### 2. Insurance

- `insuranceId` — Primary key
- `insuranceCompany` — Insurance provider
- `policyNumber` — Policy number
- `expiryDate` — Insurance expiry date

### 3. ServiceRecord

- `serviceId` — Primary key
- `serviceDate` — Date of service
- `serviceType` — Maintenance or repair type
- `serviceCost` — Service cost

## 🔗 Hibernate Relationships

- **Unidirectional `@OneToOne`:** A vehicle references one insurance record.
- **Unidirectional `@OneToMany`:** A vehicle references multiple service records.
- **`@JoinColumn`:** Defines the database join columns for entity associations.
- **Cascade configuration:** Selected according to the intended lifecycle and retention requirements of related records.

## 📋 Application Menu

| Choice | Operation |
|---|---|
| 1 | Insert vehicle, insurance, and service records |
| 2 | Retrieve a vehicle by ID |
| 3 | Retrieve all vehicles |
| 4 | Update vehicle details |
| 5 | Delete a vehicle by ID |
| 6 | Delete all vehicle records |
| Default | Exit the application |

## ⚙️ Setup and Execution

### Prerequisites

Install the following:

- JDK 17 or later
- Maven
- MySQL Server
- Eclipse, IntelliJ IDEA, or another Java IDE

### Step 1: Create the Database

Create a MySQL database:

```sql
CREATE DATABASE vehicle_management;
```

### Step 2: Configure Hibernate

Update your Hibernate configuration with your MySQL database URL, username, password, and JDBC driver settings.

Example database URL:

```text
jdbc:mysql://localhost:3306/vehicle_management
```

Use your own MySQL credentials. Ensure the Hibernate configuration registers all three entity classes.

### Step 3: Build the Project

Open a terminal in the project directory and execute:

```bash
mvn clean install
```

### Step 4: Run the Application

Run the `com.app.client.Test` class from your IDE, or configure Maven to execute the main class.

Follow the console menu to perform vehicle management operations.

## ⚠️ Exception Handling

The application defines custom exceptions to provide meaningful error messages:

- `VehicleNotFoundException`
- `InsuranceNotFoundException`
- `ServiceRecordNotFoundException`
- `VehicleOperationException`

Database operations should use appropriate transactions and reliably close Hibernate sessions. Related-record deletion must follow the configured cascade strategy to avoid unintentionally removing records that need to be retained.

## 🎯 Learning Outcomes

- Understanding Hibernate ORM and entity mapping
- Implementing unidirectional entity relationships
- Performing CRUD operations with Hibernate sessions
- Managing transactions and database resources
- Creating and handling custom exceptions
- Organizing a standalone Maven application using layered packages

## 👨‍💻 Author

**Sumit Kanade**

---

*This project is intended for learning and practising Core Java, Hibernate ORM, and MySQL integration.*
