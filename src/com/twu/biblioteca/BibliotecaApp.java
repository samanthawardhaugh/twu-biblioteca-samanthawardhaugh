package com.twu.biblioteca;

/*
Checkout Book - As a librarian, I would like customers to be able to check-out a book. Checked out books should not appear in the list of all library books.
Successful Checkout - As a customer, I would like to know that a book has been checked out by seeing the message “Thank you! Enjoy the book”.
Unsuccessful Checkout - As a customer, I would like to be notified if the book I tried to check-out is not available by seeing the message, “That book is not available.”, so that I know that I need to select a different book or fix my spelling error.
Return Book - As a librarian, I would like customers to be able to return a book, so that other customers can check that book out. Returned books should appear in the list of library books.
Successful Return - As a customer, I would like to be notified if the book I am returning belongs to this library by seeing the message, “Thank you for returning the book.”, so that I know I returned the book to the right library.
Unsuccessful Return - As a customer, I would like to be notified if the book I am returning has not been added to this library by seeing the message, “That is not a valid book to return.”, so that I can return it to the correct library or fix my spelling error.
*/

import java.util.Scanner;

public class BibliotecaApp {
    public static Library currentLibrary;
    private static Scanner scanner;
    public static Boolean quit;

    public BibliotecaApp() {
        currentLibrary = new Library();
        scanner = new Scanner(System.in);
        quit = false;
    }

    public static void main(String[] args) {
        String welcome = welcome();
        System.out.println(welcome);

        String menu = displayCustomerMenu();
        System.out.println(menu);

        while (quit == false) {
            String input = scanner.nextLine();
            String output = validateUserInput(input);
            System.out.print(output);
        }
    }

    static void setQuitStatus(Boolean status){
        quit = status;
    }

    static String welcome() {
        return "Hello there! Welcome to Biblioteca!";
    }

    static String printBooks() {
        return currentLibrary.toString();
    }

    static String displayCustomerMenu(){
        return "Options:\nList books";
    }

    static String validateUserInput(String input){
        String output = "";

        if (input.equalsIgnoreCase("quit")) {
            setQuitStatus(false);
        } else if (input.equalsIgnoreCase("list books")) {
            output = printBooks();
        } else if (input.startsWith("Check out")) {
            Boolean realBook = checkOutValidBook(input);
            if (realBook == false) {
                output = "That book is not available.\n";
            } else {
                output = "Thank you! Enjoy the book\n";
            }
        } else {
            output = "Select a valid option!\n";
        }

        return output;
    }

    static Boolean checkOutValidBook(String input) {
        String bookTitle = input.replace("Check out ", "").trim();
        Integer book_pos = currentLibrary.getBookPosition(bookTitle);

        if (book_pos == -1) {
            return false;
        } else {
            currentLibrary.checkOutBookInLibrary(book_pos);
            return true;
        }
    }
}