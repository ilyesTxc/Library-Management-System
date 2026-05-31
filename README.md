# Library Management System

## Project Description

This project is a small Java-based Library Management System built to practice object-oriented programming concepts and Java collections.

The system models a simple library where books can be stored, searched, borrowed, returned, and tracked through loans. It uses abstraction, inheritance, interfaces, generics, repositories, and collection classes such as `HashMap`, `ArrayList`, and `TreeMap`.

The main goal of the project is to organize library data cleanly while separating responsibilities between classes. Entities such as `Book`, `Member`, `Librarian`, and `Loan` represent the core data, while `BookRepository` handles book storage and `Library` contains the main business logic.

## Problem Solved

The project solves the problem of managing books and loans in a library system.

It allows the library to:

* Store books using a repository.
* Search for books by ID or author.
* Track available book copies.
* Allow members to borrow books.
* Create loan records with borrow and due dates.
* Return books and update available copies.
* List overdue loans based on their due dates.

A `TreeMap` is used to store loans by due date, which allows efficient date-based queries. Instead of scanning every loan manually, overdue loans can be found using ordered map operations such as `headMap()`.

## Classes

### `Person`

`Person` is an abstract class that represents a general person in the system.

It contains common attributes:

* `id`
* `name`

It also defines an abstract method:

* `role()`

This class is used to practice abstraction. Since a generic person does not have a specific role by itself, subclasses must provide their own implementation.

### `Member`

`Member` extends `Person`.

It represents a library member who can borrow books. It implements the `role()` method and can keep track of borrowed book IDs.

Main responsibilities:

* Store member information.
* Define the member role.
* Track borrowed books.

### `Librarian`

`Librarian` extends `Person`.

It represents a librarian in the library system. It also implements the `role()` method.

Main responsibilities:

* Store librarian information.
* Define the librarian role.

### `Book`

`Book` represents a book in the library.

It contains:

* `id`
* `title`
* `author`
* `availableCopies`

Main responsibilities:

* Store book information.
* Track how many copies are available.
* Decrease available copies when a book is borrowed.
* Increase available copies when a book is returned.
* Override `equals()` and `hashCode()` so books can be compared by ID.

### `Loan`

`Loan` represents a borrowing operation.

It contains:

* `bookId`
* `memberId`
* `borrowDate`
* `dueDate`

Main responsibilities:

* Store which book was borrowed.
* Store which member borrowed the book.
* Store the borrow date.
* Store the due date.

### `Repository<T>`

`Repository<T>` is a generic interface used to define basic storage operations.

It contains:

* `save(T item)`
* `findById(String id)`
* `findAll()`

This interface is used to practice interfaces and generics. It allows different types of repositories to follow the same structure.

### `BookRepository`

`BookRepository` implements `Repository<Book>`.

It stores books using:

* `HashMap<String, Book>`

The key is the book ID, and the value is the `Book` object.

Main responsibilities:

* Save books.
* Find books by ID in constant time.
* Return all stored books.

### `Library`

`Library` is the service class that connects everything together.

It contains the main business logic of the project.

Main responsibilities:

* Add books.
* Find books by ID.
* Get all books.
* Search books by author.
* Borrow books.
* Return books.
* Track loans.
* List overdue loans.

The `Library` class uses a `TreeMap<LocalDate, List<Loan>>` to store loans by due date. This makes it possible to efficiently retrieve loans that are due before or on a given date.

## Collections Used

### `HashMap`

Used in `BookRepository` to store books by ID.

This allows fast lookup:

```java
bookStore.get(id)
```

### `ArrayList`

Used to store lists of books, members, librarians, and loans.

### `TreeMap`

Used in `Library` to store loans ordered by due date.

This allows range queries such as:

```java
headMap(today, true)
```

which returns all loans with due dates before or equal to today.

## Future Work
