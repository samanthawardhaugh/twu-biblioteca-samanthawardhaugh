package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Scanner;

/*
User Accounts - Login - As a librarian, I want to know who has checked out a book, so that I can hold them accountable for returning it. Users must now login using their library number (which is in the format xxx-xxxx) and a password in order to check-out and return books. User credentials are predefined, so registering new users is not part of this story.

User Accounts - User information - As a customer, I want to be able to see my user information (name, email address and phone number), so that I know that the library can contact me. This option should only be available when the customer is logged in and should only display that customer’s information.*/

public class BibliotecaApp {
    public HashMap<String, User> userList;
    public Library currentLibrary;
    public Scanner scanner;
    public User currentUser;
    public Boolean quit;

    public BibliotecaApp() {
        userList = new HashMap<String, User>();
        currentLibrary = new Library();
        scanner = new Scanner(System.in);
        currentUser = null;
        quit = false;

        fillWithTestData();
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
        String loginOption = "Log in\n";
        if (currentUser != null) {
            loginOption = "Log out\nView user details\n";
        }
        String output = "Options:\n" + loginOption
                        + "List books\nList movies\n" +
                            "Check out item\nReturn item\nQuit";
        return output;
    }

    public String validateUserInput(String input){
        String output = "";
        String lowerInput = input.toLowerCase();
        if (lowerInput.equals("quit")) {
            setQuitStatus(true);
        } else if (lowerInput.equals("menu")) {
            output = displayCustomerMenu();
        } else if (lowerInput.equals("log in")) {
            output = login();
        } else if (lowerInput.equals("log out")) {
            output = logout();
        } else if (lowerInput.equals("view user details")) {
            output = getUserDetails();
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
        if (currentUser == null) {
            return "You must be logged in to check out an item!\n";
        }

        String title = input.replaceAll("^(?i)check out ", "").trim();
        Book bookToCheckOut = currentLibrary.getBook(title);
        if (bookToCheckOut == null) {
            Movie movieToCheckOut = currentLibrary.getMovie(title);
            if (movieToCheckOut == null) {
                return "That item is not available.\n";
            } else {
                movieToCheckOut.setCheckedOut(true);
                currentLibrary.updateMovie(movieToCheckOut);
                HashMap<String, Movie> usersMovies = currentUser.getBorrowedMovies();
                usersMovies.put(movieToCheckOut.getTitle(), movieToCheckOut);
                currentUser.setBorrowedMovies(usersMovies);
                updateUser(currentUser);
                return "Thank you! Enjoy the movie\n";
            }
        } else {
            bookToCheckOut.setCheckedOut(true);
            currentLibrary.updateBook(bookToCheckOut);
            HashMap<String, Book> usersBooks = currentUser.getBorrowedBooks();
            usersBooks.put(bookToCheckOut.getTitle(), bookToCheckOut);
            currentUser.setBorrowedBooks(usersBooks);
            updateUser(currentUser);
            return "Thank you! Enjoy the book\n";
        }
    }

    public String returnItem(String input) {
        if (currentUser == null) {
            return "You must be logged in to return an item!\n";
        }

        String title = input.replaceAll("^(?i)return ", "").trim();

        if (currentUser.getBorrowedBooks().containsKey(title)) {
            Book bookToReturn = currentLibrary.getBook(title);
            bookToReturn.setCheckedOut(false);
            currentLibrary.updateBook(bookToReturn);
            currentUser.getBorrowedBooks().remove(title);
            updateUser(currentUser);
            return "Thank you for returning the book.\n";
        } else if (currentUser.getBorrowedMovies().containsKey(title)) {
            Movie movieToReturn = currentLibrary.getMovie(title);
            movieToReturn.setCheckedOut(false);
            currentLibrary.updateMovie(movieToReturn);
            currentUser.getBorrowedMovies().remove(title);
            updateUser(currentUser);
            return "Thank you for returning the movie.\n";
        } else {
            return "That is not a valid item to return.\n";
        }
    }

    public String login() {

        if (currentUser != null) {
            return "Please log out before logging in as a new user!\n";
        }

        String outcome = "Invalid username and/or password\n";

        System.out.println("Please enter your library number:");
        String libnum = this.scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = this.scanner.nextLine();

        if (userList.containsKey(libnum.trim())) {
            User potentialUser = userList.get(libnum.trim());
            if (potentialUser.getPassword().equals(password.trim())) {
                currentUser = potentialUser;
                outcome = "Thank you for logging in " + potentialUser.getName() + "\n";
            }
        }

        return outcome;
    }

    public String logout() {
        if (currentUser == null) {
            return "You must be logged in to log out!\n";
        } else {
            currentUser = null;
            return "You have been successfully logged out!\n";
        }
    }

    public String getUserDetails() {
        if (currentUser == null) {
            return "You are not logged in!\n";

        } else {
            return currentUser.toString();
        }
    }

    public void updateUser(User updatedUser) {
        userList.put(updatedUser.getName(), updatedUser);
    }

    public void fillWithTestData() {
        Book bookOne = new Book();
        bookOne.setAuthor("Mr Dude");
        bookOne.setTitle("It's Your Friend Mr Dude");
        bookOne.setYear(2002);

        Book bookTwo = new Book();
        bookTwo.setAuthor("Katie Kate");
        bookTwo.setTitle("Hawkeye: An auto/biography");
        bookTwo.setYear(2010);

        Book bookThree = new Book();
        bookThree.setAuthor("Steven Universe");
        bookThree.setTitle("Figure Out What You Want To Say");
        bookThree.setYear(2015);

        currentLibrary.bookList.put("It's Your Friend Mr Dude", bookOne);
        currentLibrary.bookList.put("Hawkeye: An auto/biography", bookTwo);
        currentLibrary.bookList.put("Figure Out What You Want To Say", bookThree);

        Movie movieOne = new Movie();
        movieOne.setDirector("Taika Waititi");
        movieOne.setRating(10);
        movieOne.setTitle("Thor: Ragnarok");
        movieOne.setYear(2017);

        Movie movieTwo = new Movie();
        movieTwo.setDirector("Tommy Wiseau");
        movieTwo.setRating(0);
        movieTwo.setTitle("The Room");
        movieTwo.setYear(2003);

        currentLibrary.movieList.put("Thor: Ragnarok", movieOne);
        currentLibrary.movieList.put("The Room", movieTwo);

        User custOne = new User();
        custOne.setName("Steven Universe");
        custOne.setEmail("rosequartz@earthmail.com");
        custOne.setPassword("stronger");
        custOne.setPhoneNumber("555-5555");
        custOne.setLibraryNumber("123-4567");
        custOne.setIsAdmin(false);
        User custTwo = new User();
        custTwo.setName("Doug Eiffel");
        custTwo.setEmail("eiffel@hephaestusmail.com");
        custTwo.setPassword("scifi");
        custTwo.setPhoneNumber("555-5555");
        custTwo.setLibraryNumber("321-4567");
        custTwo.setIsAdmin(false);
        User custThree = new User();
        custThree.setName("Merle Highchurch");
        custThree.setEmail("panservice@bureauofbalance.com");
        custThree.setPassword("ZONE OF TRUTH");
        custThree.setPhoneNumber("555-5555");
        custThree.setLibraryNumber("100-5876");
        custThree.setIsAdmin(false);

        userList.put("Steven Universe", custOne);
        userList.put("Doug Eiffel", custTwo);
        userList.put("Merle Highchurch", custThree);

        User lib = new User();
        lib.setName("Bob Belcher");
        lib.setLibraryNumber("937-1173");
        lib.setPassword("baby you can chive my car");
        lib.setIsAdmin(false);

        userList.put("Bob Belcher", lib);
    }

}