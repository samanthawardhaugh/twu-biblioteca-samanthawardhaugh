package com.twu.biblioteca;

import java.util.Scanner;

/*
List Movies - As a customer, I would like to see a list of available movies, so that I can browse for a movie that I might check-out. Movies have a name, year, director and movie rating (from 1-10 or unrated).

Check-out Movie - As a customer, I would like to check out a movie from the library, so I can enjoy it at home.

User Accounts - Login - As a librarian, I want to know who has checked out a book, so that I can hold them accountable for returning it. Users must now login using their library number (which is in the format xxx-xxxx) and a password in order to check-out and return books. User credentials are predefined, so registering new users is not part of this story.

User Accounts - User information - As a customer, I want to be able to see my user information (name, email address and phone number), so that I know that the library can contact me. This option should only be available when the customer is logged in and should only display that customer’s information.*/

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
        return currentLibrary.printBookList();
    }

    public String printMovies() {
        return currentLibrary.printMovieList();
    }

    public String displayCustomerMenu(){
        return "Options:\nList books\tList movies\tQuit";
    }

    public String validateUserInput(String input){
        String output = "";
        String lowerInput = input.toLowerCase();
        if (lowerInput.equals("quit")) {
            setQuitStatus(true);
        } else if (lowerInput.equals("list books")) {
            output = printBooks();
        } else if (lowerInput.equals("list movies")) {
            output = printMovies();
        } else if (lowerInput.startsWith("check out")) {
            String response = checkOutItem(input);
            output = response;
        } else if (lowerInput.startsWith("return")) {
            String response = returnItem(input);
            output = response;
        } else {
            output = "Select a valid option!\n";
        }

        return output;
    }

    public String checkOutItem(String input) {
        String ret_str = "That item is not available.\n";

        String title = input.replace("Check out ", "").trim();
        Integer book_pos = currentLibrary.getBookPosition(title);

        if (book_pos == -1) {
            Integer movie_pos = currentLibrary.getMoviePosition(title);
            if (movie_pos == -1) {
                return ret_str;
            } else {
                currentLibrary.checkOutMovieInLibrary(movie_pos);
                ret_str = "Thank you! Enjoy the movie\n";
                return ret_str;
            }
        } else {
            currentLibrary.checkOutBookInLibrary(book_pos);
            ret_str = "Thank you! Enjoy the book\n";
            return ret_str;
        }
    }

    public String returnItem(String input) {
        String ret_str = "That is not a valid item to return.\n";

        String title = input.replace("Return ", "").trim();
        Integer book_pos = currentLibrary.getBookPosition(title);

        if (book_pos == -1) {
            Integer movie_pos = currentLibrary.getMoviePosition(title);
            if (movie_pos == -1) {
                return ret_str;
            } else {
                currentLibrary.returnMovieInLibrary(movie_pos);
                ret_str = "Thank you for returning the movie.\n";
                return ret_str;
            }
        } else {
            currentLibrary.returnBookInLibrary(book_pos);
            ret_str = "Thank you for returning the book.\n";
            return ret_str;
        }
    }

}