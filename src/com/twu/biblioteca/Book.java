package com.twu.biblioteca;

public class Book {
    public String title;
    public String author;
    public Integer year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        String returnStr = title + "(" + year.toString() + ") by " + author;
        return returnStr;
    }
}