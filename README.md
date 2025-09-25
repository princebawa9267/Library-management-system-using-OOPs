# 📚 Library Management System

A comprehensive, console-based Library Management System built with Java. This project demonstrates core Object-Oriented Programming (OOP) principles and simulates the essential functions of a university library, from user registration to book borrowing and returns.

The system uses an in-memory `ArrayList` to manage data, making it a straightforward and effective showcase of Java collections and CLI application structure.

---

## ✨ Features

* **User Management**: Add new students to the library system with their name, university roll number, and course.
* **Book Management**: Add new books to the library's collection with a title, author, and unique ISBN.
* **Issue & Return Books**: Seamlessly issue books to users and process their returns.
* **Live Availability Check**: Check if a book is currently available or has been issued to another user.
* **View All Records**: Display a complete list of all registered users and all books in the library catalog.
* **Interactive CLI**: A user-friendly, menu-driven command-line interface for all operations.
* **Pre-loaded Data**: The application starts with dummy data for immediate testing and demonstration.

---

## 💻 Example Usage

``` bash
Hello sir, Welcome 


1. Add new user
2. Add new book
3. Check book Availability
4. Issue book
5. Return book
6. Show all students
7. Show all books
8. Exit
Choose (1-8) from above options : 7
0 Title "Effective Java (3rd Edition) " Author : Joshua Bloch ISBN : 978-0134685991, Availablity : true
1 Title "Python Crash Course (2nd Edition) " Author : Eric Matthes ISBN : 978-1593279288, Availablity : true
...

1. Add new user
...
8. Exit
Choose (1-8) from above options : 4
Choose mode to Issue book 
1. Student Library id number
2. Student University id number
1
Enter Library Id number : 1
Enter the book ISBN number : 978-0134685991
INFO : Added book : Effective Java (3rd Edition)
Book is added to Karan Aujla's account

1. Add new user
...
8. Exit
Choose (1-8) from above options : 6
All user that exists in database


Library id : 1
Name : Karan Aujla
University roll number : 12201101
Course : BTECH_COMPUTER_SCIENCE
Books borrowed : 1. Effective Java (3rd Edition)   

Library id : 2
Name : Robin Sarwara
University roll number : 12201102
Course : BTECH_COMPUTER_SCIENCE
Books borrowed : NULL

...

1. Add new user
...
8. Exit
Choose (1-8) from above options : 8
```

## 📂 Project Structure

```bash
Library-Management-System/
└── src/
    ├── Main.java       # Entry point, menu loop
    ├── Library.java    # Core logic
    ├── User.java       # Student data model
    ├── Book.java       # Book data model
    └── Course.java     # Enum for courses
```

## 🏗️ Classes & Relationships
**1. Library Class**
The central engine of the system, managing all core operations and data collections.
```
    public class Library {
        private List<User> users;       // Aggregation: Library HAS-A list of Users
        private List<Book> books;       // Aggregation: Library HAS-A list of Books
    }
```

_Relationships:_
✅ Aggregation with User and Book (Users and Books can conceptually exist without the Library).

_Methods Used:_
- addUser(), addBook() → To populate the library's collections.
- issueBook() → Finds a user and a book, validates, and links them.
- returnBook() → Finds a book, validates, and unlinks it from a user.

_issueBook() Workflow:_
```
graph TD
    A[Find User by ID] --> B{User Exists?};
    B -->|Yes| C[Find Book by ISBN];
    C --> D{Book Exists & Available?};
    D -->|Yes| E[Add Book to User's List];
    E --> F[Mark Book as Unavailable];
```
**2. User Class**
Represents a student member who can borrow books.
```
class User {
    private static Long sequence = 0L; // For auto-generating IDs
    private final Long id;             // Unique, auto-incremented library ID
    private final String name;
    private final Long uniRollNo;        // University-provided roll number
    private final Course course;
    private List<Book> borrowedBooks;  // Association: User HAS-A list of borrowed Books
}
```

_Relationships:_
- ✅ Association with Book (A user can have multiple borrowed books).

_Methods Used:_
- addBook() → Adds a book to the user's borrowedBooks list.
- removeBook() → Removes a book from the user's list upon return.

**3. Book Class**
Represents a single book in the library's catalog.
```
class Book {
    private final String isbn;          // Unique identifier (Immutable)
    private final String author;
    private final String name;

    private boolean isAvailable;
    private Long bookIssuedToLibraryId; // Tracks which user has the book
}
```
_Relationships:_ 
- A core entity, does not own other custom objects.

_Methods Used:_
- isBookAvailable() → Checks the current availability status.
- setAvailable() → Updates the book's status when issued or returned.

**4. Course Enum**
A type-safe enumeration to define a fixed set of valid courses for users.
```
enum Course {
    BTECH_COMPUTER_SCIENCE,
    BTECH_MECHANICAL_ENGINEER,
    BCA,
    // ... and others
}
```

_Purpose:_
- Ensures that users can only be created with valid, pre-defined courses, preventing data errors.

## 🛠️ How It Works
- The application's logic is centered around an interactive loop that processes user commands.
- The Main class initializes a Library object and pre-loads it with sample data.
- A while loop starts, displaying a menu of 8 options to the user.
- The user selects an operation by entering a number (1-8).
- A modern switch statement calls the corresponding method from the Library class.
- The method performs the requested action (e.g., finds a user, updates a book's status) and prints feedback to the console.
- The menu is displayed again, awaiting the next command until the user chooses to Exit (8).


## Application Flow Diagram

```
graph TD
    A[Start] --> B{Initialize Library};
    B --> C{Pre-load Dummy Users & Books};
    C --> D{Enter Main Loop};
    D --> E[Display Menu Options];
    E --> F{Get User Choice};
    F --> G{Process Choice via Switch};
    G -- 1-7 --> H[Execute Library Method];
    H --> D;
    G -- 8 --> I[Exit Program];
    I --> J[End];

```

## 💡 Design Choices & Key Concepts

This project was built to demonstrate several fundamental software engineering concepts using Java.
- OOP (Object-Oriented Programming): The system is designed with clear object boundaries. User and Book are pure data objects, while Library acts as a service class that encapsulates all business logic, creating a clean separation of concerns.
- In-Memory Database (ArrayList): An ArrayList is used to store the collections of users and books. For a small-scale console application, this is a simple and effective choice. It demonstrates collection management, but it also highlights a trade-off: searching for a user or book by ID requires iterating the list, resulting in O(n) time complexity. For a larger system, a HashMap (keyed by ID/ISBN) would provide O(1) lookups.
- Enum for Type Safety (Course.java): Using an enum for Course ensures that a User can only be created with a valid, predefined course. This prevents runtime errors and invalid data that could arise from using simple String types.
- Static Members for ID Generation: The User class contains a private static Long sequence to automatically generate a unique, auto-incrementing library ID for each new user. This is a simple and effective way to manage unique identifiers without an external database.
- Modern Java Features: The code utilizes modern language features like the Enhanced switch Statement (from Java 14+) for cleaner, more readable control flow and a Labeled break to exit the main loop cleanly.
- Console I/O: User interaction is handled robustly using the java.util.Scanner class.

## 🚀 How to Run

- Prerequisites
 - Java Development Kit (JDK) 14 or higher.

- Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/princebawa9267/Library-management-system-using-OOPs.git
   ```
2. Compile all Java files:
    ```bash
    javac *.java
    ```
3. Run the main application:
   ```bash
   java Main
   ```

## 🔮 Future Enhancements

- Data Persistence: Implement file I/O (CSV/JSON) or a lightweight database (SQLite) to save data between sessions.
- Advanced Error Handling: Add try-catch blocks to gracefully handle invalid user inputs (e.g., text instead of numbers).
- Search Functionality: Add features to search for books by title or author, and users by name.
- Unit Testing: Develop JUnit tests to ensure the reliability of the business logic in the Library class.
- Fine/Due Date System: Implement logic to calculate due dates for borrowed books and manage fines for late returns.

## 💬 Acknowledgements

- This project was developed with assistance from AI tools for guidance and documentation.
- Google Gemini: Helped structure, write, and refine this README.md file by merging concepts from a previous project.








