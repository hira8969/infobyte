# Online Examination System

A Java Swing based Online Examination System built with Maven, MVC, OOP, repository and service layers. The application simulates a real student exam flow with secure login, profile update, timed MCQ exam, manual and automatic submission, result calculation, detailed answer breakdown, and logout.

## Features

- Default login: `student` / `student123`
- Profile update with validation
- 20 in-memory Java MCQ questions
- One-question-at-a-time exam screen
- Previous and next navigation with answer retention
- 30-minute countdown using `javax.swing.Timer`
- Manual submit confirmation
- Automatic submit when time reaches `00:00`
- Result screen with score, percentage, pass/fail status, time taken, and answer breakdown
- Session handling to protect exam and result state

## Technology Stack

- Java 17
- Java Swing
- Maven
- MVC Architecture
- In-memory repositories, ready for JDBC replacement

## Folder Structure

```text
src/main/java/com/exam
├── constants
├── controller
├── model
├── repository
├── service
├── util
├── view
└── Main.java
```

## Execution Steps

```bash
mvn clean compile
mvn exec:java
```

## Architecture

Views contain Swing components only. Controllers receive events and coordinate screen transitions. Services contain authentication, exam state, and result logic. Repositories manage in-memory data. Models represent users, questions, exams, answers, and results.

## Screenshots

Add screenshots here after running the application:

- Login Screen
- Dashboard
- Exam Screen
- Result Screen

## Future Scope

- JDBC and MySQL/SQLite persistence
- Student registration
- Admin dashboard
- Subject-wise exams
- Random question generation
- PDF result export
- Email notifications
- Online proctoring

## Author

BCA Internship Project
