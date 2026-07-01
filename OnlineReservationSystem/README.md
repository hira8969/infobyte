# Online Reservation System

Java Swing desktop application for booking and cancelling train reservations using MVC architecture, JDBC, and SQLite.

## Features

- Secure login using database credentials.
- Train number lookup with automatic train name filling.
- Ticket booking with unique PNR generation.
- Reservation confirmation dialog.
- Reservation lookup and cancellation by PNR.
- Input validation for all important fields.
- SQLite database setup from SQL scripts.

## Technology Stack

- Java 17+
- Java Swing
- JDBC
- SQLite
- Maven

## Default Login

- Username: `admin`
- Password: `admin123`

## Folder Structure

```text
OnlineReservationSystem
|-- pom.xml
|-- README.md
|-- docs
|   |-- SRS.md
|   `-- diagrams
|       |-- class-diagram.puml
|       |-- er-diagram.puml
|       |-- use-case-diagram.puml
|       `-- workflow-diagram.puml
`-- src
    `-- main
        |-- java
        |   `-- com
        |       `-- onlinereservation
        |           |-- Main.java
        |           |-- config
        |           |-- controller
        |           |-- dao
        |           |-- model
        |           |-- service
        |           |-- util
        |           `-- view
        `-- resources
            |-- schema.sql
            `-- sample_data.sql
```

## MVC Architecture

- `model`: Plain Java classes such as `User`, `Train`, and `Reservation`.
- `view`: Swing frames for login, dashboard, booking, and cancellation.
- `controller`: Handles button clicks and coordinates between views and services.
- `service`: Contains validation and business logic.
- `dao`: Contains JDBC queries.
- `config`: Database connection and startup setup.
- `util`: Reusable helper classes.

## Installation Steps

1. Install JDK 17 or newer.
2. Install Maven.
3. Open this folder in IntelliJ IDEA, Eclipse, or VS Code.
4. Run Maven build:

```bash
mvn clean package
```

## Running Instructions

Run the desktop app with:

```bash
mvn exec:java
```

On first launch, the app creates `data/online_reservation.db` and loads default users/trains from `src/main/resources`.

## Database Setup

The database is initialized automatically from:

- `src/main/resources/schema.sql`
- `src/main/resources/sample_data.sql`

## Screenshots

Add screenshots here after running the application:

- Login Screen
- Dashboard
- Reservation Form
- Cancellation Form
- Success Dialog

## Author

Academic Java Full Stack Desktop Project.
