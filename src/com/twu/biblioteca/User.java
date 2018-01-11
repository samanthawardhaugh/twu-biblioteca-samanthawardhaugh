package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String name;
    private String libraryNumber;
    private String password;
    private String email;
    private String phoneNumber;
    private HashMap<String, Book> borrowedBooks = new HashMap<String, Book>();
    private HashMap<String, Movie> borrowedMovies = new HashMap<String, Movie>();
    private Boolean isAdmin;

    public User() {
        name = "";
        libraryNumber = "invalid";
        password = "";
        isAdmin = false;
    }

    public User(String newName, String newLibraryNumber) {
        name = newName;
        libraryNumber = newLibraryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HashMap<String, Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(HashMap<String, Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public HashMap<String, Movie> getBorrowedMovies() {
        return borrowedMovies;
    }

    public void setBorrowedMovies(HashMap<String, Movie> borrowedMovies) {
        this.borrowedMovies = borrowedMovies;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        String userDetails = "Name: " + this.name + "\nEmail: " + this.email + "\nPhone Number: " + this.phoneNumber + "\n";
        return userDetails;
    }
}
