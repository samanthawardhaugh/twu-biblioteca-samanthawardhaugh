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
        String expected = "Options:\nList books";
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
        Boolean expected = false;
        assertEquals(expected, tester.quit);
    }

    @Test
    public void displayCustomerHomePageTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String expected = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        String actual = tester.printBooks();
        assertEquals(expected, actual);
    }
}
