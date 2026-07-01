# Software Requirements Specification

## 1. Abstract

Number Guessing Game is a Java Swing desktop application where a player chooses a difficulty level and guesses a randomly generated number. The project demonstrates MVC architecture, OOP principles, service-layer business logic, validation, statistics, and unit testing.

## 2. Introduction

The application is designed as an academic/internship project for learning Java desktop development. It separates user interface code from business logic so that the system is easier to maintain, test, and extend.

## 3. Objectives

- Generate a random target number for every round.
- Allow the player to choose Easy, Medium, or Hard difficulty.
- Validate guesses and display friendly messages.
- Track attempts, timer, round history, best score, statistics, and leaderboard.
- Provide a clean Java Swing interface.

## 4. Problem Statement

Beginners often build small games with all logic placed in one class. This project solves that problem by implementing a simple game using professional structure and clean separation of concerns.

## 5. Scope

The project covers local desktop gameplay only. Leaderboard data is stored in memory during the running session.

## 6. Functional Requirements

- The system shall ask for player name and difficulty.
- The system shall generate a number within the selected range.
- The system shall accept numeric guesses only.
- The system shall display Too High, Too Low, Correct, or You Lost.
- The system shall end a round on win or attempt limit.
- The system shall store completed round results.
- The system shall display statistics and leaderboard.
- The system shall allow the player to play again or exit.

## 7. Non-Functional Requirements

- The application shall run on Java 17 or later.
- The GUI shall fit a 1000x700 non-resizable window.
- Business logic shall be testable without Swing.
- Code shall follow Java naming conventions.

## 8. System Design

The application follows MVC:

- Model: game state and domain data.
- View: Swing screens and tables.
- Controller: event coordination.
- Service: business rules, validation flow, statistics, and history.
- Utility: reusable random number generation and validation.

## 9. UML Diagrams

PlantUML files are available in `docs/uml`:

- `use-case.puml`
- `class-diagram.puml`
- `sequence-diagram.puml`
- `activity-diagram.puml`

## 10. Screenshots Placeholder

Screenshots can be inserted after running the project.

## 11. Testing

JUnit test cases cover:

- Correct guess
- Too high
- Too low
- Invalid input
- Attempt limit reached
- Play again
- Difficulty selection
- Statistics calculation

Run tests with:

```bash
mvn test
```

## 12. Future Enhancements

- Persistent leaderboard storage
- Exportable reports
- More difficulty levels
- Theme selection
- Multiplayer mode

## 13. Conclusion

The project provides a complete, maintainable Number Guessing Game using Java Swing and MVC architecture. It is simple enough for academic learning while still following practical software design principles.
