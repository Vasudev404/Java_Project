# BookFlow Design Document

## Project
Library Management System – BookFlow

## Objective
BookFlow manages books, registered users, librarian administration, borrowing, returning and transaction records using Java OOP, Swing and limited SQL.

## MVC Design

### Model
The Model represents the application's data and business rules.

**Book**
- id
- title
- author
- ISBN
- genre
- status

Methods include getters/setters and `toString()`.

**User**
- id
- user ID
- name
- contact
- username
- password
- borrowed books

Business rule: a user can have a maximum of 3 active borrowed books.

**Librarian**
- Inherits from User.
- Represents an administrator/librarian.

**Library**
- Stores collections of books and users.

**BorrowTransaction**
- transaction ID
- book
- user
- borrow date
- return date

**LibraryService**
- Central business logic for login, book management, user management and circulation.

### View
Swing GUI classes:
- LoginView
- DashboardView

The View is responsible for displaying forms, tables, buttons and messages. It does not contain SQL statements.

### Controller
- BookFlowApplication starts the application.
- LoginController processes login events.
- DashboardController processes dashboard events, search, book management, user registration and circulation.

### Repository
The repository layer contains the limited SQL/JDBC code:
- Database
- BookRepository
- UserRepository
- TransactionRepository

## Important OOP concepts

1. **Encapsulation:** model fields are private and accessed through methods.
2. **Inheritance:** Librarian extends User.
3. **Abstraction:** LibraryService exposes library operations without making the View handle SQL.
4. **Polymorphism:** a User reference can hold a Librarian object.
5. **Association:** BorrowTransaction associates a Book with a User.

## Circulation flow

```text
User clicks Borrow
       |
       v
DashboardController
       |
       v
LibraryService
       |
       +--> Find book
       +--> Find user
       +--> Check AVAILABLE
       +--> Check 3-book limit
       |
       v
TransactionRepository
       |
       v
Book status = BORROWED
```

Return performs the reverse operation and also verifies that the selected user actually has an active transaction for that book.
