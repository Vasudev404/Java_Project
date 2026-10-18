# BookFlow User Manual

## Step 1 – Install Java
Install JDK 17 or newer. Verify:

```bash
java -version
```

## Step 2 – Install VS Code
Install Visual Studio Code and the **Extension Pack for Java**.

## Step 3 – Install Maven
Install Apache Maven and verify:

```bash
mvn -version
```

## Step 4 – Open BookFlow
In VS Code choose **File → Open Folder** and select the `BookFlow` folder.

## Step 5 – Run
Open Terminal → New Terminal:

```bash
mvn clean compile
mvn exec:java
```

## Step 6 – Librarian login
Choose **Librarian Login**.

Username: `admin`
Password: `admin123`

Librarians can manage books/users and circulation.

## Step 7 – User login
Choose **User Login**.

Username: `user1`
Password: `user123`

A normal user can search books and borrow/return using their own User ID.

## Step 8 – Add a book
Librarian → Book Management → enter title, author, ISBN and genre → Add Book.

## Step 9 – Search
Search accepts a value that can match title, author, ISBN or genre.

## Step 10 – Borrow
Open Circulation:
1. Enter ISBN.
2. For librarian login, enter the registered User ID.
3. For user login, the system automatically uses the logged-in user's ID.
4. Click Borrow.

The system checks availability and the maximum of 3 active books.

## Step 11 – Return
Enter the ISBN and User ID, then click Return. The system verifies that this user has actually borrowed the book.

## Step 12 – GitHub
Create an empty repository called `BookFlow` on GitHub. In the VS Code terminal:

```bash
git init
git add .
git commit -m "Initial BookFlow project"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/BookFlow.git
git push -u origin main
```

After editing:

```bash
git add .
git commit -m "Update BookFlow"
git push
```
