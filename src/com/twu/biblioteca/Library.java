package com.twu.biblioteca;

import java.util.HashMap;

public class Library {
    public HashMap<String, Book> bookList = new HashMap<String, Book>();
    public HashMap<String, Movie> movieList = new HashMap<String, Movie>();

    public Library() {
    }

    public Book getBook(String bookTitle) {
        if (this.bookList.containsKey(bookTitle)) {
            return this.bookList.get(bookTitle);
        } else {
            return null;
        }
    }

    public Movie getMovie(String movieTitle) {
        if (this.movieList.containsKey(movieTitle)) {
            return this.movieList.get(movieTitle);
        } else {
            return null;
        }
    }

    public void updateBook(Book updatedBook) {
        this.bookList.put(updatedBook.getTitle(), updatedBook);
    }

    public void updateMovie(Movie updatedMovie) {
        this.movieList.put(updatedMovie.getTitle(), updatedMovie);
    }

    public Integer getBookPosition(String bookTitle) {

        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equalsIgnoreCase(bookTitle)) {
                return i;
            }
        }
        return -1;
    }

    public Integer getMoviePosition(String movieTitle) {

        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTitle().equalsIgnoreCase(movieTitle)) {
                return i;
            }
        }
        return -1;
    }

    public void checkOutBookInLibrary(Integer book) {
        this.bookList.get(book).setCheckedOut(true);
    }

    public void returnBookInLibrary(Integer book) {
        this.bookList.get(book).setCheckedOut(false);
    }

    public void checkOutMovieInLibrary(Integer movie) {
        this.movieList.get(movie).setCheckedOut(true);
    }

    public void returnMovieInLibrary(Integer movie) {
        this.movieList.get(movie).setCheckedOut(false);
    }

    public HashMap<String,Book> getBookList() {
        return bookList;
    }

    public void setBookList(HashMap<String,Book> bookList) {
        this.bookList = bookList;
    }

    public HashMap<String,Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(HashMap<String,Movie> movieList) {
        this.movieList = movieList;
    }

    public String printBookList() {
        String returnStr = "";

        for (String key: this.bookList.keySet()) {
            if (this.bookList.get(key).getCheckedOut() == false) {
                returnStr = returnStr + this.bookList.get(key).toString();
                returnStr = returnStr + "\n";
            }
        }
        return returnStr;
    }

    public String printMovieList() {
        String returnStr = "";
        for (String key: this.movieList.keySet()) {
            if (this.movieList.get(key).getCheckedOut() == false) {
                returnStr = returnStr + this.movieList.get(key).toString();
                returnStr = returnStr + "\n";
            }
        }
        return returnStr;
    }
}
