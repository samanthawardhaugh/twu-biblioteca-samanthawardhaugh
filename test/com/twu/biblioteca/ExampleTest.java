package com.twu.biblioteca;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void greetingTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String actual = tester.welcome();

        assertEquals("Hello there! Welcome to Biblioteca!", actual);
    }

    @Test
    public void displayMainMenuTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String menu = tester.displayCustomerMenu();
        String expected = "Options:\nList books\tList movies\tQuit";
        assertEquals(expected, menu);
    }

    @Test
    public void selectValidMenuOption() {
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("List Books");
        String expected = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        assertEquals(expected, output);
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
        String actual = tester.printBooks();
        assertEquals(expected, actual);
    }

    @Test
    public void displayListOfMoviesTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String expected = "Thor: Ragnarok (2017) - 10/10\nThe Room (2003) - 0/10\n";
        String actual = tester.printMovies();
        assertEquals(expected, actual);
    }

    @Test
    public void checkOutBookSuccessfullyTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Check out Figure Out What You Want To Say");
        String expected = "Thank you! Enjoy the book\n";
        assertEquals(expected, output);
    }

    @Test
    public void checkOutBookUnsuccessfullyTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Check out The Adventure Zone");
        String expected = "That item is not available.\n";
        assertEquals(expected, output);
    }

    @Test
    public void checkOutMovieSuccessfullyTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Check out The Room");
        String expected = "Thank you! Enjoy the movie\n";
        assertEquals(expected, output);
    }

    @Test
    public void checkOutMovieUnsuccessfullyTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String output = tester.validateUserInput("Check out Sawbones");
        String expected = "That item is not available.\n";
        assertEquals(expected, output);
    }

    @Test
    public void updateBookListOnCheckoutTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String bookList = tester.validateUserInput("List Books");
        String expectedBooks = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        assertEquals(expectedBooks, bookList);

        tester.validateUserInput("Check out Figure Out What You Want To Say");

        String updatedBookList = tester.validateUserInput("List Books");
        String newExpectedBooks = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\n";
        assertEquals(newExpectedBooks, updatedBookList);
    }

    @Test
    public void updateMovieListOnCheckoutTest() {
        BibliotecaApp tester = new BibliotecaApp();
        String movieList = tester.validateUserInput("List Movies");
        String expectedMovies = "Thor: Ragnarok (2017) - 10/10\nThe Room (2003) - 0/10\n";
        assertEquals(expectedMovies, movieList);

        tester.validateUserInput("Check out The Room");

        String updatedMovieList = tester.validateUserInput("List Movies");
        String newExpectedMovies = "Thor: Ragnarok (2017) - 10/10\n";
        assertEquals(newExpectedMovies, updatedMovieList);
    }

    @Test
    public void returnBookSuccessfullyTest(){
        BibliotecaApp tester = new BibliotecaApp();
        tester.validateUserInput("Check out Figure Out What You Want To Say");
        String output = tester.validateUserInput("Return Figure Out What You Want To Say");
        String expected = "Thank you for returning the book.\n";
        assertEquals(expected, output);
    }

    @Test
    public void returnBookUnsuccessfullyTest(){
        BibliotecaApp tester = new BibliotecaApp();
        tester.validateUserInput("Check out Figure Out What You Want To Say");
        String output = tester.validateUserInput("Return Figure Out What You Want");
        String expected = "That is not a valid item to return.\n";
        assertEquals(expected, output);
    }

    @Test
    public void returnMovieSuccessfullyTest(){
        BibliotecaApp tester = new BibliotecaApp();
        tester.validateUserInput("Check out The Room");
        String output = tester.validateUserInput("Return The Room");
        String expected = "Thank you for returning the movie.\n";
        assertEquals(expected, output);
    }

    @Test
    public void returnMovieUnsuccessfullyTest(){
        BibliotecaApp tester = new BibliotecaApp();
        tester.validateUserInput("Check out The Room");
        String output = tester.validateUserInput("Return The Roo");
        String expected = "That is not a valid item to return.\n";
        assertEquals(expected, output);
    }

    @Test
    public void updateBookListOnReturnTest() {
        BibliotecaApp tester = new BibliotecaApp();

        tester.validateUserInput("Check out Figure Out What You Want To Say");

        String bookList = tester.validateUserInput("List Books");
        String expectedBooks = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\n";
        assertEquals(expectedBooks, bookList);

        String output = tester.validateUserInput("Return Figure Out What You Want To Say");

        String updatedBookList = tester.validateUserInput("List Books");
        String newExpectedBooks = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        assertEquals(newExpectedBooks, updatedBookList);
    }

    @Test
    public void updateMovieListOnReturnTest() {
        BibliotecaApp tester = new BibliotecaApp();

        tester.validateUserInput("Check out The Room");

        String movieList = tester.validateUserInput("List Movies");
        String expectedMovies = "Thor: Ragnarok (2017) - 10/10\n";
        assertEquals(expectedMovies, movieList);

        String output = tester.validateUserInput("Return The Room");

        String updatedMovieList = tester.validateUserInput("List Movies");
        String newExpectedMovies = "Thor: Ragnarok (2017) - 10/10\nThe Room (2003) - 0/10\n";
        assertEquals(newExpectedMovies, updatedMovieList);
    }
}
