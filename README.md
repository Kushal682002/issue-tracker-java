# Issue Tracker

A Java-based Issue Tracker application developed using Core Java, Maven, DAO and Service Layer architecture.

The application allows users to report issues, assign issues to available assignees, update issue status, filter issues and delete old resolved/closed issues.

---

## Features

- Report a new issue
- Validate issue details
- Automatically assign an available assignee
- Maintain active issue count for assignees
- Update issue status
- Prevent incompatible status changes
- Filter issues by status or assignee
- Delete old resolved/closed issues
- Exception handling using a custom exception
- Logging using Log4j2
- Unit testing using JUnit

---

## Technologies Used

- Java
- Maven
- JUnit
- Apache Commons Configuration
- Apache Commons Logging
- Log4j2
- Java Collections
- Java Streams
- Regular Expressions
- Object-Oriented Programming

---

## Project Architecture

The project follows a layered architecture:

```text
                    IssueTester
                        |
                        v
                Service Layer
                        |
          +-------------+-------------+
          |                           |
          v                           v
      Validator                    DAO Layer
                                      |
                            +---------+---------+
                            |                   |
                            v                   v
                    IssueDAOImpl        AssigneeDAOImpl