# Software Requirements Specification

## Abstract

The Online Examination System is a desktop GUI application that allows a student to login, update profile details, attend a timed MCQ examination, submit answers, and view a complete result report.

## Introduction

The system is designed as an academic Java Swing project using MVC architecture and layered code organization. It separates presentation, control flow, business logic, repository access, utility behavior, and domain models.

## Objectives

- Provide a secure student login.
- Simulate a timed online examination.
- Store selected answers during navigation.
- Calculate score and detailed result statistics.
- Provide maintainable MVC-based source code.

## Problem Statement

Manual examination workflows are time-consuming and error-prone. This system demonstrates how a computerized exam can collect answers, manage time, submit automatically, and produce an instant result.

## Scope

Phase 1 uses in-memory collections. The repository layer keeps the design ready for Phase 2 database integration with JDBC and MySQL or SQLite.

## Functional Requirements

- Login with username and password.
- Reset and exit actions on login screen.
- Dashboard with student and exam information.
- Profile update for display name and password.
- Start timed exam.
- Display one question with four radio options.
- Navigate previous and next.
- Retain saved answers.
- Warn before manual submission.
- Auto submit on timer expiry.
- Show result and answer breakdown.
- Logout and clear session.

## Non-Functional Requirements

- Professional Swing UI.
- Layered MVC architecture.
- Low coupling and high cohesion.
- User-friendly error messages.
- Beginner-friendly clean code.

## System Design

The application uses Model, View, Controller, Service, Repository, Utility, and Constants packages. Controllers handle UI events. Services implement business rules. Repositories provide data. Views only display Swing components and expose event methods.

## Testing

Manual test cases are available in `docs/TEST_CASES.md`.

## Future Enhancements

Database integration, Spring Boot APIs, admin dashboard, student registration, subject-wise exams, random question selection, PDF result export, email notifications, and proctoring.

## Conclusion

The project satisfies the core requirements of an Online Examination System while preserving a clean architecture suitable for academic submission and future extension.
