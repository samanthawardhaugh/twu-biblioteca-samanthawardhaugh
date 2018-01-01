package com.twu.biblioteca;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ExampleTest {
    private static final OutputStream os = new ByteArrayOutputStream();
    private static final PrintStream ps = new PrintStream(os);

    @BeforeClass
    public static void setupOutputStream() {
        System.setOut(ps);
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
    public void selectUserTest() {
        BibliotecaApp tester = new BibliotecaApp();
        Integer user = tester.checkUser();
        Integer expectedValue = 1;
        assertEquals(expectedValue, user);
    }

    @Test
    public void displayCustomerHomePageTest(){
        BibliotecaApp tester = new BibliotecaApp();
        String expected = "It's Your Friend Mr Dude(2002) by Mr Dude\nHawkeye: An auto/biography(2010) by Katie Kate\nFigure Out What You Want To Say(2015) by Steven Universe\n";
        String actual = tester.printBooks();
        assertEquals(expected, actual);
    }
}
