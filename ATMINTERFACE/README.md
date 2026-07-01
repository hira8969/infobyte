# ATM Interface System

Console-based ATM simulation built with Java 17, Maven, MVC architecture, OOP, and layered services.

## Features

- Login using User ID and 4-digit PIN
- Maximum 3 failed login attempts
- Transaction history
- Withdraw, deposit, and fund transfer
- Balance inquiry and account information
- In-memory repository using `HashMap` and `ArrayList`
- JUnit test cases for core service behavior

## Technology Stack

- Java 17+
- Maven
- JUnit 5
- Core Java collections
- MVC + Repository + Service Layer patterns

## Folder Structure

```text
src/main/java/com/atm
├── Main.java
├── constants
├── controller
├── exception
├── model
├── repository
├── service
├── util
└── view
```

## Execution Steps

```bash
mvn clean test
mvn exec:java -Dexec.mainClass="com.atm.Main"
```

If your Maven installation does not have the exec plugin cached, run:

```bash
mvn clean package
java -cp target/classes com.atm.Main
```

## Sample Accounts

| User ID | PIN  | Account Holder | Opening Balance |
|---------|------|----------------|-----------------|
| 1001    | 1234 | Hiralal Kumar  | Rs. 50000       |
| 1002    | 4321 | Rahul Sharma   | Rs. 30000       |
| 1003    | 5678 | Priya Singh    | Rs. 45000       |

## Architecture

The project follows strict MVC:

- Model: `Account`, `User`, `Transaction`, `TransactionType`
- View: `ATMView`
- Controller: `ATMController`
- Service: `AuthenticationService`, `BankingService`
- Repository: `BankRepository`
- Utility: `ValidationUtil`, `DateTimeUtil`

## Screenshots Placeholder

Add console screenshots here after running the application.

## Future Scope

- JDBC and MySQL integration
- Spring Boot REST API
- JavaFX GUI
- OTP verification
- Email/SMS notification
- Admin dashboard
- Audit logs and card blocking

## Author Information

Academic Java project for internship/BCA-level ATM Interface task.
