package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {
    public ArrayList<Book> bookList = new ArrayList<Book>();

    public Library() {
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

        bookList.add(bookOne);
        bookList.add(bookTwo);
        bookList.add(bookThree);
    }

    public String toString() {
        String returnStr = "";
        for (int i = 0; i < bookList.size(); i++) {
            returnStr = returnStr + bookList.get(i).toString();
            returnStr = returnStr + "\n";
        }
        return returnStr;
    }
}
