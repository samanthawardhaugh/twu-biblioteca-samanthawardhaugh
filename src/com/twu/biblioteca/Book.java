package com.twu.biblioteca;

public class Book extends LIbrary_Item{
    public String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        String returnStr = title + "(" + year.toString() + ") by " + author;
        return returnStr;
    }
}