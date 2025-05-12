/**
 * Program Name: Homework #6
 * Author: Erik Porter
 * Date: 05/12/2025
 * Description: This program implements a simple Library Management System using Java.
 * It allows users to add, remove, view, search, check out, and return books using user input.
 * The system uses ArrayLists to manage the book collection and demonstrates object oriented programming
 * through the use of Book and Library classes. Input validation and user friendly messages are included
 * for a smooth user experience.
 */
package hw6;

import java.util.ArrayList;
import java.util.Scanner;

//Book class
// Represents a book with title, author, ISBN, and availability status
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // Constructor to initialize a book with title, author, and ISBN
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;  // New books are available by default
    }

    // Getter for ISBN
    public String getIsbn() {
        return isbn;
    }

    // Check if the book is available for checkout
    public boolean isAvailable() {
        return isAvailable;
    }

    // Mark the book as checked out
    public void checkOut() {
        isAvailable = false;
    }

    // Mark the book as returned (available)
    public void returnBook() {
        isAvailable = true;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Return a string representation of the books details
    public String toString() {
        return "Title: " + title + ", Author: " + author +
               ", ISBN: " + isbn + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

// Library class
// Manages a collection of books and provides operations to modify and search the collection
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    // Add a new book if the ISBN doesn't already exist
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                System.out.println("A book with this ISBN already exists.");
                return;
            }
        }
        books.add(book);
        System.out.println("Book added successfully.");
    }

    // Remove a book by ISBN
    public void removeBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                books.remove(b);
                System.out.println("Book removed.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Display all books currently in the library
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Search books by title (case-insensitive)
    public void searchByTitle(String title) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No books found with that title.");
    }

    // Search books by author (case-insensitive)
    public void searchByAuthor(String author) {
        boolean found = false;
        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No books found with that author.");
    }

    // Check out a book if it's available
    public void checkOutBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                if (!b.isAvailable()) {
                    System.out.println("Book is already checked out.");
                } else {
                    b.checkOut();
                    System.out.println("Book checked out.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Return a book if it's currently checked out
    public void returnBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                if (b.isAvailable()) {
                    System.out.println("Book is already available.");
                } else {
                    b.returnBook();
                    System.out.println("Book returned.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

// Main class 
// Provides a text based menu interface for interacting with the library
public class Main {
    public static void main(String[] args) {
        Library library = new Library();  // Create a Library object
        Scanner scanner = new Scanner(System.in);
        int choice = 0;  // Store user's menu choice

        // Menu loop
        do {
            System.out.println("\nLibrary Menu: ");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            //user menu selection and handle invalid inputs
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;  // Skip rest of loop if input is invalid
            }

            // Handle user's menu choice
            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;
                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    library.removeBook(scanner.nextLine());
                    break;
                case 3:
                    library.displayAllBooks();
                    break;
                case 4:
                    System.out.print("Enter title to search: ");
                    library.searchByTitle(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter author to search: ");
                    library.searchByAuthor(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter ISBN to check out: ");
                    library.checkOutBook(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Enter ISBN to return: ");
                    library.returnBook(scanner.nextLine());
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 8);  // Loop continues until user selects Exit

        scanner.close();  // Close the scanner resource once user hits (8) or exit
    }
}