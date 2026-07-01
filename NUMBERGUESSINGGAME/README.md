# Number Guessing Game

A Java 17 Swing application built with a clean MVC architecture. The game generates a random number based on the selected difficulty, validates guesses, gives hints, tracks attempts, stores round history, calculates statistics, and maintains a local in-memory leaderboard.

## Features

- Java Swing GUI with welcome, gameplay, and result screens
- MVC architecture with model, view, controller, service, utility, and constants packages
- Difficulty levels: Easy, Medium, Hard
- Input validation for empty, non-numeric, and out-of-range guesses
- Attempt tracking, timer, best score, game statistics, round history, and leaderboard
- JUnit 5 tests for core gameplay and statistics

## Technology Stack

- Java 17+
- Java Swing
- Maven
- JUnit 5

## Folder Structure

```text
src/main/java/com/game
  controller/GameController.java
  model/
  service/GameService.java
  util/
  view/
  constants/GameConstants.java
  Main.java
src/test/java/com/game/service/GameServiceTest.java
docs/
  SRS.md
  uml/*.puml
```

## Installation And Execution

```bash
mvn clean test
mvn package
java -jar target/number-guessing-game-1.0.0.jar
```

You can also run `com.game.Main` directly from IntelliJ IDEA, Eclipse, or VS Code.

## Screenshots

Add screenshots here after running the application:

- Welcome screen
- Gameplay screen
- Result screen
- History and leaderboard table

## Future Enhancements

- Persist leaderboard to a JSON or database file
- Add sound effects and keyboard shortcuts
- Add user profiles
- Export game history as CSV

## Author Details

Name: BCA Student  
Project: Number Guessing Game  
Architecture: Java MVC
