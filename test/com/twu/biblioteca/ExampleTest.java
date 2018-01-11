package com.twu.biblioteca;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExampleTest {
    private static final OutputStream os = new ByteArrayOutputStream();
    private static final PrintStream ps = new PrintStream(os);

    @BeforeClass
    public static void setupOutputStream() {
        //System.setOut(ps);
    }

    @AfterClass
    public static void resetOutputStream() {
        //System.setOut(null);
    }

    public BibliotecaApp login() {
        String usernamePasswordStream = "Doug Eiffel\nscifi\n";
        InputStream outStream = new ByteArrayInputStream(usernamePasswordStream.getBytes());
        System.setIn(outStream);
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Log in");
        System.setIn(System.in);
        tester.scanner = new Scanner(System.in);
        return tester;
    }

    @Test
    public void greetingTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String actual = tester.welcome();

        assertEquals("Hello there! Welcome to Biblioteca!", actual);
    }

    @Test
    public void loginSuccessfullyTest() {
        String usernamePasswordStream = "Doug Eiffel\nscifi\n";
        InputStream outStream = new ByteArrayInputStream(usernamePasswordStream.getBytes());
        System.setIn(outStream);

        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Log in");
        String expected = "Thank you for logging in Doug Eiffel\n";
        assertEquals(expected,output);
    }

    @Test
    public void loginUnsuccessfullyTest() {
        String usernamePasswordStream = "Doug Eiffel\ndumbidiot\n";
        InputStream outStream = new ByteArrayInputStream(usernamePasswordStream.getBytes());
        System.setIn(outStream);

        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Log in");
        String expected = "Invalid username and/or password\n";
        assertEquals(expected,output);
    }

    @Test
    public void loginWhileLoggedInTest() {
        BibliotecaApp tester = login();
        String output = tester.login();
        String expected = "Please log out before logging in as a new user!\n";

        assertEquals(expected, output);
    }

    @Test
    public void logoutSuccessfullyTest() {
        BibliotecaApp tester = login();
        String output = tester.logout();
        String expected = "You have been successfully logged out!\n";

        assertEquals(expected, output);
    }

    @Test
    public void logoutWhileLoggedOutTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.logout();
        String expected = "You must be logged in to log out!\n";
        assertEquals(expected,output);
    }

    @Test
    public void getUserDetailsLoggedInTest() {
        BibliotecaApp tester = login();
        String output = tester.validateUserInput("View User Details");
        String expected = "Name: Doug Eiffel\n" +
                          "Email: eiffel@hephaestusmail.com\n" +
                          "Phone Number: 555-5555\n";
        assertEquals(expected, output);
    }

    @Test
    public void getUserDetailsLoggedOutTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("View User Details");
        String expected = "You are not logged in!\n";
        assertEquals(expected, output);
    }

    @Test
    public void displayLoggedOutMainMenuTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String menu = tester.displayCustomerMenu();
        String expected = "Options:\nLog in\n"
                + "List books\nList movies\n" +
                "Check out item\nReturn item\nQuit";
        assertEquals(expected, menu);
    }

    @Test
    public void displayLoggedInMainMenuTest() {
        BibliotecaApp tester = login();
        String menu = tester.displayCustomerMenu();
        String expected = "Options:\nLog out\nView user details\n"
                + "List books\nList movies\n" +
                "Check out item\nReturn item\nQuit";
        assertEquals(expected, menu);
    }

    @Test
    public void selectValidMenuOption() {
        BibliotecaApp tester = new BibliotecaApp();
        String expected = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        List<String> expectedList = Arrays.asList(expected.split("\n"));
        Collections.sort(expectedList);
        String actual = tester.validateUserInput("List Books");
        List<String> actualList = Arrays.asList(actual.split("\n"));
        Collections.sort(actualList);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void selectInvalidMenuOption() {
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Help meee");
        String expected = "Select a valid option!\n";
        assertEquals(expected, output);
    }

    @Test
    public void quitTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("quit");
        Boolean expected = true;
        assertEquals(expected, tester.quit);
    }

    @Test
    public void displayListOfBooksTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String expected = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        List<String> expectedList = Arrays.asList(expected.split("\n"));
        Collections.sort(expectedList);
        String actual = tester.printBooks();
        List<String> actualList = Arrays.asList(actual.split("\n"));
        Collections.sort(actualList);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void displayListOfMoviesTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String expected = "Thor: Ragnarok (2017) - 10/10\nThe Room (2003) - 0/10\n";
        List<String> expectedList = Arrays.asList(expected.split("\n"));
        Collections.sort(expectedList);
        String actual = tester.printMovies();
        List<String> actualList = Arrays.asList(actual.split("\n"));
        Collections.sort(actualList);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void checkOutItemLoggedOutTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Check out The Adventure Zone");
        String expected = "You must be logged in to check out an item!\n";
        assertEquals(expected, output);
    }

    @Test
    public void checkOutBookSuccessfullyLoggedInTest(){
        BibliotecaApp loggedInTester = login();
        String output = loggedInTester.validateUserInput("Check out Figure Out What You Want To Say");
        String expected = "Thank you! Enjoy the book\n";
        assertEquals(expected, output);
    }

    @Test
    public void checkOutBookUnsuccessfullyLoggedInTest(){
        BibliotecaApp loggedInTester = login();
        String output = loggedInTester.validateUserInput("Check out The Adventure Zone");
        String expected = "That item is not available.\n";
        assertEquals(expected, output);
    }

    @Test
    public void checkOutMovieSuccessfullyLoggedInTest(){
        BibliotecaApp loggedInTester = login();
        String output = loggedInTester.validateUserInput("Check out The Room");
        String expected = "Thank you! Enjoy the movie\n";
        assertEquals(expected, output);
    }

    @Test
    public void checkOutMovieUnsuccessfullyLoggedInTest(){
        BibliotecaApp loggedInTester = login();
        String output = loggedInTester.validateUserInput("Check out Sawbones");
        String expected = "That item is not available.\n";
        assertEquals(expected, output);
    }

    @Test
    public void updateBookListOnCheckoutTest() {
        BibliotecaApp loggedInTester = login();
        String bookString = loggedInTester.validateUserInput("List Books");
        List<String> bookList = Arrays.asList(bookString.split("\n"));
        Collections.sort(bookList);

        String expectedBookString = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        List<String> expectedBookList = Arrays.asList(expectedBookString.split("\n"));
        Collections.sort(expectedBookList);

        assertEquals(expectedBookList, bookList);

        loggedInTester.validateUserInput("Check out Figure Out What You Want To Say");

        String updatedBookString = loggedInTester.validateUserInput("List Books");
        List<String> updatedBookList = Arrays.asList(updatedBookString.split("\n"));
        Collections.sort(updatedBookList);
        String newExpectedBooks = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\n";
        List<String> newExpectedBookList = Arrays.asList(newExpectedBooks.split("\n"));
        Collections.sort(newExpectedBookList);

        assertEquals(newExpectedBookList, updatedBookList);
    }

    @Test
    public void updateMovieListOnCheckoutTest() {
        BibliotecaApp loggedInTester = login();
        String movieString = loggedInTester.validateUserInput("List Movies");
        List<String> movieList = Arrays.asList(movieString.split("\n"));
        Collections.sort(movieList);

        String expectedMovieString = "Thor: Ragnarok (2017) - 10/10\nThe Room (2003) - 0/10\n";
        List<String> expectedMovieList = Arrays.asList(expectedMovieString.split("\n"));
        Collections.sort(expectedMovieList);

        assertEquals(expectedMovieList, movieList);

        loggedInTester.validateUserInput("Check out The Room");

        String updatedMovieString = loggedInTester.validateUserInput("List Movies");
        List<String> updatedMovieList = Arrays.asList(updatedMovieString.split("\n"));
        Collections.sort(updatedMovieList);
        String newExpectedMovies = "Thor: Ragnarok (2017) - 10/10\n";
        List<String> newExpectedMovieList = Arrays.asList(newExpectedMovies.split("\n"));
        Collections.sort(newExpectedMovieList);

        assertEquals(newExpectedMovieList, updatedMovieList);
    }

    @Test
    public void returnBookLoggedOutTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Return The Adventure Zone");
        String expected = "You must be logged in to return an item!\n";
        assertEquals(expected, output);
    }

    @Test
    public void returnBookSuccessfullyLoggedInTest(){
        BibliotecaApp loggedInTester = login();
        loggedInTester.validateUserInput("Check out Figure Out What You Want To Say");
        String output = loggedInTester.validateUserInput("Return Figure Out What You Want To Say");
        String expected = "Thank you for returning the book.\n";
        assertEquals(expected, output);
    }

    @Test
    public void returnBookUnsuccessfullyLoggedInTest(){
        BibliotecaApp loggedInTester = login();
        loggedInTester.validateUserInput("Check out Figure Out What You Want To Say");
        String output = loggedInTester.validateUserInput("Return Figure Out What You Want");
        String expected = "That is not a valid item to return.\n";
        assertEquals(expected, output);
    }

    @Test
    public void returnMovieSuccessfullyLoggedInTest(){
        BibliotecaApp loggedInTester = login();
        loggedInTester.validateUserInput("Check out The Room");
        String output = loggedInTester.validateUserInput("Return The Room");
        String expected = "Thank you for returning the movie.\n";
        assertEquals(expected, output);
    }

    @Test
    public void returnMovieUnsuccessfullyLoggedInTest(){
        BibliotecaApp loggedInTester = login();
        loggedInTester.validateUserInput("Check out The Room");
        String output = loggedInTester.validateUserInput("Return The Roo");
        String expected = "That is not a valid item to return.\n";
        assertEquals(expected, output);
    }

    @Test
    public void updateBookListOnReturnLoggedInTest() {
        BibliotecaApp loggedInTester = login();

        loggedInTester.validateUserInput("Check out Figure Out What You Want To Say");

        String bookString = loggedInTester.validateUserInput("List Books");
        List<String> bookList = Arrays.asList(bookString.split("\n"));
        Collections.sort(bookList);

        String expectedBookString = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\n";
        List<String> expectedBookList = Arrays.asList(expectedBookString.split("\n"));
        Collections.sort(expectedBookList);
        assertEquals(expectedBookList, bookList);

        String output = loggedInTester.validateUserInput("Return Figure Out What You Want To Say");

        String updatedBookString = loggedInTester.validateUserInput("List Books");
        List<String> updatedBookList = Arrays.asList(updatedBookString.split("\n"));
        Collections.sort(updatedBookList);
        String newExpectedBooks = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        List<String> newExpectedBookList = Arrays.asList(newExpectedBooks.split("\n"));
        Collections.sort(newExpectedBookList);

        assertEquals(newExpectedBookList, updatedBookList);
    }

    @Test
    public void updateMovieListOnReturnLoggedInTest() {
        BibliotecaApp loggedInTester = login();

        loggedInTester.validateUserInput("Check out The Room");

        String movieString = loggedInTester.validateUserInput("List Movies");
        List<String> movieList = Arrays.asList(movieString.split("\n"));
        Collections.sort(movieList);
        String expectedMovieString = "Thor: Ragnarok (2017) - 10/10\n";
        List<String> expectedMovieList = Arrays.asList(expectedMovieString.split("\n"));
        Collections.sort(expectedMovieList);
        assertEquals(expectedMovieList, movieList);

        String output = loggedInTester.validateUserInput("Return The Room");

        String updatedMovieString = loggedInTester.validateUserInput("List Movies");
        List<String> updatedMovieList = Arrays.asList(updatedMovieString.split("\n"));
        Collections.sort(updatedMovieList);
        String newExpectedMovies = "Thor: Ragnarok (2017) - 10/10\nThe Room (2003) - 0/10\n";
        List<String> newExpectedMovieList = Arrays.asList(newExpectedMovies.split("\n"));
        Collections.sort(newExpectedMovieList);
        assertEquals(newExpectedMovieList, updatedMovieList);
    }
}
