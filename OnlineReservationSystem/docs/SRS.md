# Software Requirements Specification

## 1. Project Abstract

The Online Reservation System is a Java desktop application that allows users to log in, book train tickets, generate PNR numbers, view reservation details, and cancel bookings. It uses Java Swing for the user interface, JDBC for database access, and SQLite for local data storage.

## 2. Introduction

Manual ticket reservation can be slow and error-prone. This project demonstrates how a small desktop application can organize reservation work with a clean MVC architecture and database-backed records.

## 3. Problem Statement

Users need a simple system to book and cancel reservations while storing data safely in a relational database. The system should validate input, avoid duplicate PNR numbers, and provide clear messages.

## 4. Objectives

- Provide login security.
- Book transport tickets.
- Generate unique PNR numbers.
- Fetch train details automatically.
- Cancel reservations using PNR.
- Store records using JDBC.

## 5. Scope

This project is designed for academic and internship demonstration. It supports one default admin user, sample trains, booking, lookup, and cancellation.

## 6. Functional Requirements

- User can log in with username and password.
- User can enter passenger and journey details.
- System auto-fills train name from train number.
- System generates unique PNR.
- User can fetch reservation details by PNR.
- User can cancel reservation after confirmation.

## 7. Non-Functional Requirements

- Desktop UI should be easy to use.
- Database queries should use PreparedStatement.
- Code should follow MVC architecture.
- Error messages should be user-friendly.
- Project should build using Maven.

## 8. Use Case Diagram

See `docs/diagrams/use-case-diagram.puml`.

## 9. Class Diagram

See `docs/diagrams/class-diagram.puml`.

## 10. ER Diagram

See `docs/diagrams/er-diagram.puml`.

## 11. Database Schema

Tables:

- `users(id, username, password)`
- `trains(train_no, train_name)`
- `reservations(id, pnr, passenger_name, train_no, train_name, class_type, journey_date, source_station, destination_station, booking_time)`

## 12. Workflow Diagram

See `docs/diagrams/workflow-diagram.puml`.

## 13. Testing Cases

| Test Case | Input | Expected Result |
| --- | --- | --- |
| Valid login | admin/admin123 | Dashboard opens |
| Invalid login | wrong/wrong | Error message |
| Empty passenger name | blank | Validation message |
| Invalid train number | abc | Validation message |
| Valid booking | complete form | PNR generated |
| Fetch valid PNR | existing PNR | Reservation details shown |
| Fetch invalid PNR | wrong PNR | Not found message |
| Cancel valid PNR | existing PNR | Reservation deleted |

## 14. Screenshots Placeholder

- Login Screen
- Dashboard Screen
- Reservation Screen
- Cancellation Screen
- Confirmation Dialog

## 15. Future Enhancements

- Add multiple user roles.
- Add hashed passwords.
- Add ticket fare calculation.
- Add printable ticket receipts.
- Add MySQL configuration option.
- Add JTable booking history.

## 16. Conclusion

The project demonstrates a complete beginner-friendly Java Swing reservation system with MVC separation, JDBC database operations, validation, and project documentation.
