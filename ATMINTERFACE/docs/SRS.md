# Software Requirements Specification

## 1. Abstract

ATM Interface System is a console-based Java banking simulation. It demonstrates secure login, account operations, transaction logging, and layered MVC design.

## 2. Introduction

The system models a simple ATM where users authenticate with a user ID and PIN, then perform common banking operations. Data is stored in memory for simplicity.

## 3. Objectives

- Simulate ATM login and banking workflows.
- Apply Java OOP, MVC, SOLID, and clean code practices.
- Maintain transaction history for every account.
- Provide tests and documentation suitable for an academic project.

## 4. Problem Statement

Manual banking operation simulations often mix UI, business logic, and data access. This project separates concerns through model, view, controller, service, repository, utility, and constants packages.

## 5. Scope

The application supports sample users only and stores data in memory. It is intended for console execution and educational demonstration.

## 6. Functional Requirements

- User can log in using valid credentials.
- User is locked out after 3 failed login attempts.
- User can check balance.
- User can deposit valid positive amounts.
- User can withdraw valid positive amounts if balance is sufficient.
- User can transfer funds to an existing account.
- User can view transaction history.
- User can view account information.
- User can logout or exit.

## 7. Non-Functional Requirements

- The code must be modular and readable.
- The system must validate user input.
- The system must show user-friendly errors.
- The system must be testable with JUnit.
- The application must run with Java 17 or newer.

## 8. System Design

The system uses MVC and service layer architecture. `ATMController` receives user actions from `ATMView`, delegates to `AuthenticationService` and `BankingService`, and updates the view. `BankRepository` stores account and user data.

## 9. Architecture Diagram

```text
Console User
    |
ATMView
    |
ATMController
    |
Services
    |
BankRepository
    |
Models
```

## 10. UML Diagrams

PlantUML diagrams are available in `docs/uml`.

## 11. Testing

JUnit tests cover login success, login failure, lockout, deposit, withdraw, transfer, balance inquiry, and transaction history.

## 12. Future Enhancements

- Database persistence using JDBC/MySQL
- Spring Boot backend
- REST APIs
- JavaFX desktop UI
- OTP verification
- Email notifications
- Admin dashboard

## 13. Conclusion

The ATM Interface System provides a complete educational Java project with real-world layering, validation, transaction logging, and tests.
