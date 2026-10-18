# BookFlow – Library Management System

BookFlow is a semester project built using **Java, OOP, Swing, MVC and limited SQL**.

## 1. Architecture

```text
Controller  --->  Model  --->  Repository/SQLite
     |
     v
   View (Swing)
```

- **Model:** Book, User, Librarian, Library, BorrowTransaction and LibraryService. Contains data and library rules.
- **View:** Swing windows/components. Displays forms, tables, buttons and messages.
- **Controller:** Handles login, button clicks, borrowing, returning and navigation.
- **Repository:** Small database layer using JDBC/SQLite. This keeps SQL away from the Swing screens.

## 2. Requirements

- JDK 17 or newer
- Visual Studio Code
- Extension Pack for Java in VS Code
- Apache Maven 3.8+ (recommended)

## 3. Run in VS Code

Open the **BookFlow** folder in VS Code.

Open the VS Code terminal and run:

```bash
mvn clean compile
mvn exec:java
```

The first run downloads the SQLite JDBC dependency automatically and creates `bookflow.db`.

If `mvn` is not recognized, install Maven and restart VS Code.

## 4. Demo accounts

Librarian:
- Username: `admin`
- Password: `admin123`

User:
- Username: `user1`
- Password: `user123`

## 5. Main features

### Librarian
- Add, update and remove books
- Search books by title, author, ISBN and genre
- Register users
- Search users by ID/name
- Borrow and return books for a user

### User
- Search books
- Borrow a book using their own User ID
- Return their own borrowed book
- Maximum 3 active borrowed books

## 6. Database

SQLite is used through JDBC. The database is created automatically. Tables:

- users
- books
- transactions

Do not commit `bookflow.db` to GitHub. It is ignored by `.gitignore`.

## 7. GitHub

From the project folder:

```bash
git init
git add .
git commit -m "Initial BookFlow project"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/BookFlow.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username.

For future changes:

```bash
git add .
git commit -m "Describe your change"
git push
```
