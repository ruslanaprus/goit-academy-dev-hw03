# Number Retrieval and Sum Calculation

This project provides a flexible and testable architecture for retrieving numbers from different sources (user input, random generation, and file reading) and performing calculations on these numbers. It was created as a solution to a task in GoIT academy Module 2.03 "Unit tests" and focuses on practicing unit testing through dependency isolation and mocking.

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Class Descriptions](#class-descriptions)
- [Key Features](#key-features)
- [Design Patterns](#design-patterns)
- [Testing](#testing)
- [Build the Project](#build-the-project)
- [Usage](#usage)

## Overview

The application demonstrates how to retrieve a number from different sources and calculate the sum of all integers from 1 to the retrieved number. The sources include:

- User input through the console
- A randomly generated number
- A number read from a file

The application emphasizes the principles of Dependency Inversion and Dependency Injection, making the code highly testable and maintainable. A Singleton pattern is also employed in the file reading component to ensure that the same file instance is reused throughout the application, which is particularly useful for testing.

## Project Structure


```shell

    󱧼 src
    ├──  main
    │   ├──  java
    │   │   └──  org
    │   │       └──  example
    │   │           ├──  AppLauncher.java
    │   │           └──  sum
    │   │               ├──  NumberFromFile.java
    │   │               ├──  NumberGetter.java
    │   │               ├──  NumberManager.java
    │   │               ├──  RandomInt.java
    │   │               ├──  SumCalculator.java
    │   │               └──  UserInput.java
    │   └──  resources
    │       ├──  logback.xml
    │       └──  number.txt
    └──  test
        ├──  java
        │   └──  org
        │       └──  example
        │           ├──  AppLauncherTest.java
        │           ├──  sum
        │           │   ├──  NumberFromFileTest.java
        │           │   ├──  RandomIntTest.java
        │           │   └──  UserInputTest.java
        │           └──  SumCalculatorTest.java
        └──  resources

```

## Class Descriptions

1. **`AppLauncher`**: Entry point of the application. It initialises and runs the application by retrieving numbers from different sources and calculating the sum using `SumCalculator`.

2. **`NumberGetter`**: An interface that defines the contract for retrieving a number. Implementations of this interface can retrieve numbers from various sources.

3. **`RandomInt`**: An implementation of `NumberGetter` that generates a random number within a specified range.

4. **`UserInput`**: An implementation of `NumberGetter` that retrieves a number from user input via the console.

5. **`NumberFromFile`**: A Singleton implementation of `NumberGetter` that reads a number from a specified file.

6. **`NumberManager`**: Manages the logic for retrieving a number using a `NumberGetter`.

7. **`SumCalculator`**: Calculates the sum of all integers from 1 to a given number.

## Key Features

- **Multiple Sources for Number Retrieval**: The application can retrieve a number from the console, generate a random number, or read a number `n` from a file.
- **Sum Calculation**: Once the number is retrieved, the `SumCalculator` calculates the sum of all integers from 1 to the retrieved number `n` using the formula `n * (n + 1) / 2`, derived from the arithmetic series sum formula.
- **Testability**: The architecture is designed to be easily testable by allowing dependencies to be injected and mocked.
- **Error Handling**: The application handles errors such as invalid input, file read errors, and others gracefully.

## Design Patterns

### Dependency Inversion

The project adheres to the Dependency Inversion Principle (DIP) by depending on abstractions (interfaces) rather than concrete implementations. The `NumberGetter` interface is used to abstract the number retrieval process, allowing for flexible and interchangeable implementations.

### Dependency Injection

The dependencies in this project are injected rather than instantiated directly, making the components loosely coupled and more testable. The `AppLauncher` class demonstrates Dependency Injection by accepting `NumberGetter` and `SumCalculator` as parameters in the `launch` method.

### Singleton Pattern

The `NumberFromFile` class implements the Singleton pattern to ensure that only one instance of the file reader exists throughout the application. This pattern also aids in testing, as it allows for the injection of mock instances.

## Testing

This project contains a suite of unit tests designed to thoroughly test the functionality of retrieving numbers from various sources, calculating their sum, and handling exceptional scenarios. The tests cover functionalities such as user input, random number generation, reading numbers from a file, and singleton pattern enforcement. Additionally, the tests make extensive use of dependency injection, mocking, and reflection to achieve comprehensive coverage.

### Test Frameworks Used
- **JUnit 5**: For unit testing.
- **Mockito**: For mocking dependencies.
- **JaCoCo**: For code coverage.

### Structure

The test codebase is organised into the following packages and classes:

- **`org.example`**
    - `AppLauncherTest`: Tests for launching the application using different implementations of `NumberGetter`.
    - `SumCalculatorTest`: Tests for verifying the correctness of summation logic.
- **`org.example.sum`**
    - `UserInputTest`: Tests for validating user input handling.
    - `RandomIntTest`: Tests for validating random number generation.
    - `NumberFromFileTest`: Tests for verifying file reading and singleton behaviour.

### Testing Approaches

### 1. **User Input Handling (`UserInputTest`)**

The `UserInputTest` class tests the ability of the application to handle and validate user input correctly. The approach used includes:

- **Simulated Input Streams:** By using `ByteArrayInputStream` to simulate user input, the tests ensure that the input handling logic correctly processes different types of inputs, such as valid integers, invalid strings, and negative numbers.
- **Test Scenarios:**
    - **Valid Input:** Checks that a valid integer is correctly processed.
    - **Invalid then Valid Input:** Ensures the application can skip over invalid inputs and ultimately return the first valid integer.
    - **Boundary Testing:** Tests the handling of minimum and large valid inputs.
    - **Invalid Inputs:** Verifies that invalid inputs like zero and negative numbers are rejected until a valid positive integer is provided.

### 2. **Random Number Generation (`RandomIntTest`)**

The `RandomIntTest` class tests the functionality of the `RandomInt` class, which generates random numbers within a specified range. The approach includes:

- **Testing with Default and Custom Ranges:** Verifies that the random number generation behaves correctly within the default (1 to 100) and custom ranges.
- **Mocking the `Random` Class:** By mocking the `Random` class, the tests can inject specific outputs and validate the behaviour of `RandomInt` under controlled conditions. This includes ensuring that edge cases like the minimum or maximum possible random number are handled correctly.
- **Exception Handling:** Tests that an exception is thrown when the range is invalid (e.g., less than 1).

### 3. **File Reading and Singleton Pattern (`NumberFromFileTest`)**

The `NumberFromFileTest` class tests the `NumberFromFile` class, which reads a number from a file and enforces a singleton pattern. The approach includes:

- **Singleton Pattern Testing:** Verifies that only one instance of `NumberFromFile` is created, ensuring consistent state across the application.
- **File Handling:** Tests reading from a file with different contents, such as:
    - **Valid numeric data:** Ensures correct number retrieval.
    - **Invalid data:** Ensures appropriate handling when the file contains non-numeric data.
    - **Empty Files:** Checks that the application throws an exception when the file is empty.
    - **Whitespace Handling:** Ensures that numbers surrounded by whitespace are correctly parsed.
- **Reflection API Usage:** Tests private methods and constructors using reflection to ensure proper handling of internal logic, such as error logging and file creation.
- **Mocking File System Interactions:** Mocks static methods in `Files` to simulate I/O exceptions and validate that the application behaves as expected in such scenarios.

### 4. **Sum Calculation (`SumCalculatorTest`)**

The `SumCalculatorTest` class verifies the correctness of the summation logic in the `SumCalculator` class. The approach includes:

- **Basic Summation Tests:** Verifies that the sum of the first `n` natural numbers is calculated correctly for various values of `n`.
- **Exception Handling:** Ensures that an exception is thrown when the input `n` is invalid (e.g., `n <= 0`).

### 5. **Application Launch and Dependency Injection (`AppLauncherTest`)**

The `AppLauncherTest` class tests the `AppLauncher` class, which is responsible for launching the application with different implementations of `NumberGetter`. The approach includes:

- **Mocking Dependencies:** By injecting mocks of `NumberGetter` and `SumCalculator`, the tests validate that the application launches correctly and that the interactions between components occur as expected.
- **Verifying Interactions:** Ensures that methods in `NumberGetter` and `SumCalculator` are called with the expected arguments, using `verify` from Mockito.
- **Exception Scenarios:** Tests how the application handles exceptions thrown by either `NumberGetter` or `SumCalculator`, ensuring that the system behaves gracefully under error conditions.
- **Multiple Launches:** Validates that the application can be launched multiple times with different inputs and ensures consistent behavior across these launches.

## Build the Project

### Prerequisites

- **Java Development Kit (JDK) 8 or higher**: Ensure that the JDK is installed and configured in your environment.
- **Gradle**: This project uses Gradle as the build tool.

### Steps to Build

To build the project, follow these steps:

1. **Clone the repository**:
```shell
git clone git@github.com:ruslanaprus/goit-academy-dev-hw03.git
cd goit-academy-dev-hw03
```
2. **Build and Test**:

The test task is part of the build lifecycle, so running the build command will automatically execute all tests and generate a JaCoCo code coverage report. You can build the project and run tests with:

```shell
./gradlew clean build
```
or run only the tests:
```shell
./gradlew test
```

After running tests, the JaCoCo coverage report will be generated automatically in the `docs` directory.

### Running the Application
After creating the fat JAR, you can run the application using the following command:
```shell
java -jar build/libs/sumcalculator.jar
```

This will execute the main method in `AppLauncher.java`, and you should see the serialized JSON string printed in the console.

## Usage
The application demonstrates retrieving numbers from different sources and calculating the sum of all integers from 1 to the retrieved number. Below are examples from the AppLauncher:

1. **Random Number Generation**: The application retrieves a random number `n` between 1 and 100 (default value) and calculates the sum:

```java
appLauncher.launch(new RandomInt(), new SumCalculator());
```
Example Output:

```css
Retrieved number: 42
Sum of numbers from 1 to 42: 903
```
2. **File Input**: The application reads a number from a file (`src/main/resources/number.txt`) and calculates the sum:

```java
appLauncher.launch(NumberFromFile.getInstance("src/main/resources/number.txt"), new SumCalculator());
```
Example Output:

```css
Retrieved number: 15
Sum of numbers from 1 to 15: 120
```
3. **User Input**: The application prompts the user to input a number via the console and calculates the sum:

```java
appLauncher.launch(new UserInput(), new SumCalculator());
```

Example Interaction:
```css
Please enter a number: 10
Retrieved number: 10
Sum of numbers from 1 to 10: 55
```

