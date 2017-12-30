package com.twu.biblioteca;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void greetingTest() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        BibliotecaApp tester = new BibliotecaApp();
        tester.main(new String[]{});

        assertEquals("Hello there! Welcome to Biblioteca!", os.toString().trim());
    }
}
