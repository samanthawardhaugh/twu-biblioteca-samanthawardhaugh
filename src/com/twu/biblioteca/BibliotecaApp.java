package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {
    public static Library currentLibrary;
    private static Scanner scanner;
    public Boolean quit;

    public BibliotecaApp() {
        currentLibrary = new Library();
        scanner = new Scanner(System.in);
        quit = false;
    }

    public static void main(String[] args) {
        BibliotecaApp tester = new BibliotecaApp();
        String welcome = tester.welcome();
        System.out.println(welcome);

        String menu = tester.displayCustomerMenu();
        System.out.println(menu);

        while (tester.getQuitStatus() == false) {
            String input = tester.scanner.nextLine();
            String output = tester.validateUserInput(input);
            System.out.print(output);
        }
    }

    public Boolean getQuitStatus(){
        return quit;
    }

    public void setQuitStatus(Boolean status){
        quit = status;
    }

    public String welcome() {
        return "Hello there! Welcome to Biblioteca!";
    }

    public String printBooks() {
        return currentLibrary.toString();
    }

    public String displayCustomerMenu(){
        return "Options:\nList books\tQuit";
    }

    public String validateUserInput(String input){
        String output = "";
        String lowerInput = input.toLowerCase();
        if (lowerInput.equals("quit")) {
            setQuitStatus(true);
        } else if (lowerInput.equals("list books")) {
            output = printBooks();
        } else if (lowerInput.startsWith("check out")) {
            Boolean realBook = checkOutBook(input);
            if (realBook == false) {
                output = "That book is not available.\n";
            } else {
                output = "Thank you! Enjoy the book\n";
            }
        } else if (lowerInput.startsWith("return")) {
            Boolean realBook = returnBook(input);
            if (realBook == false) {
                output = "That is not a valid book to return.\n";
            } else {
                output = "Thank you for returning the book.\n";
            }
        } else {
            output = "Select a valid option!\n";
        }

        return output;
    }

    public Boolean checkOutBook(String input) {
        String bookTitle = input.replace("Check out ", "").trim();
        Integer book_pos = currentLibrary.getBookPosition(bookTitle);

        if (book_pos == -1) {
            return false;
        } else {
            currentLibrary.checkOutBookInLibrary(book_pos);
            return true;
        }
    }

    public Boolean returnBook(String input) {
        String bookTitle = input.replace("Return ", "").trim();
        Integer book_pos = currentLibrary.getBookPosition(bookTitle);

        if (book_pos == -1) {
            return false;
        } else {
            currentLibrary.returnBookInLibrary(book_pos);
            return true;
        }
    }

}