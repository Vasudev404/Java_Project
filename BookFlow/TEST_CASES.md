# BookFlow Test Cases

| ID | Test | Expected Result |
|---|---|---|
| TC01 | Login with admin/admin123 using Librarian Login | Librarian dashboard opens |
| TC02 | Login with user1/user123 using User Login | User dashboard opens |
| TC03 | Wrong password | Login rejected |
| TC04 | Add book with valid details | Book appears in search |
| TC05 | Search by title | Matching book appears |
| TC06 | Search by author | Matching book appears |
| TC07 | Search by ISBN | Matching book appears |
| TC08 | Search by genre | Matching book appears |
| TC09 | Register a new user | User appears in User Management |
| TC10 | Borrow an available book | Transaction created and status becomes BORROWED |
| TC11 | Borrow same book again | Operation rejected because book is borrowed |
| TC12 | Borrow more than 3 active books | Fourth borrow rejected |
| TC13 | Return book by actual borrower | Status becomes AVAILABLE and transaction gets return date |
| TC14 | User attempts to return another user's book | Return rejected |
| TC15 | Librarian removes available book | Book removed |
| TC16 | Librarian tries to remove borrowed book | Book is not removed |
| TC17 | Logout | Login screen appears |
