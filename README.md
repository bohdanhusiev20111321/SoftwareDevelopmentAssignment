Game Idea Planner

A Kotlin console application for managing Developers and their Game Ideas.
Created as part of the Software Development Tools module at SETU by Bohdan Husiev (20111321).

Overview

    The application provides functionality for:

    Managing Developers
    Managing Ideas
    Simple reports (ideas by genre, completed ideas by retired developers, etc.)
    JSON-based saving and loading
    Validation of job titles, genres and statuses
    The project is structured into logical packages and includes unit tests, documentation generation and code coverage reports.

Project Structure
src
├─ main
│   └─ kotlin/ie/setu
│        ├─ controller
│        │     ├─ DeveloperManager.kt
│        │     └─ IdeaManager.kt
│        ├─ main/menu
│        │     ├─ DeveloperMenu.kt
│        │     └─ IdeaMenu.kt
│        ├─ model
│        │     ├─ Developer.kt
│        │     └─ Idea.kt
│        ├─ utils
│        │     ├─ JsonFileStore.kt
│        │     ├─ MenuUtils.kt
│        │     └─ ValidationUtils.kt
│        └─ Main.kt
└─ test
    └─ kotlin/ie/setu/controller
          ├─ DeveloperManagerTest.kt
          └─ IdeaManagerTest.kt




The main menu provides access to Developer and Idea management.

Unit Tests (JUnit 5)

Tests cover:
Adding items
Listing data
Finding by ID
Basic manager behaviour

Run tests:
./gradlew test

Code Coverage (Jacoco)
Generate coverage report:
./gradlew jacocoTestReport


Open: build/reports/jacoco/test/html/index.html



Documentation (Dokka)
Output: build/dokka/html/index.html


JSON Persistence

The app stores data using kotlinx.serialization and saves/loads through JsonFileStore.kt.

Input Validation

Validation (genres, statuses, job titles) is handled by ValidationUtils.kt to ensure correct user input.

Main Features
Developers
Add, update, remove
List developers
Find developer by ID
Ideas
Add, update, remove
List ideas and filter by genre

Reports:
Number of ideas per developer
Completed ideas for retired developers

Example Main Menu Output
--- Main Menu ---
1. Manage Developers
2. Manage Ideas
0. Exit



Technologies Used
Kotlin (JVM)
Gradle
JUnit 5
Jacoco
Dokka
Kotlinx Serialization

Build the Project
./gradlew clean build

Author

Bohdan Husiev
Software Development Tools – SETU
Game Idea Planner (Kotlin Console Application)
