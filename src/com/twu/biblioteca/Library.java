package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {
    public ArrayList<Book> bookList = new ArrayList<Book>();
    public ArrayList<Movie> movieList = new ArrayList<Movie>();

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

        Movie movieOne = new Movie();
        movieOne.setDirector("Taika Waititi");
        movieOne.setRating(10);
        movieOne.setTitle("Thor: Ragnarok");
        movieOne.setYear(2017);

        Movie movieTwo = new Movie();
        movieTwo.setDirector("Tommy Wiseau");
        movieTwo.setRating(0);
        movieTwo.setTitle("The Room");
        movieTwo.setYear(2003);

        movieList.add(movieOne);
        movieList.add(movieTwo);
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
        bookList.get(book).setCheckedOut(true);
    }

    public void returnBookInLibrary(Integer book) {
        bookList.get(book).setCheckedOut(false);
    }

    public void checkOutMovieInLibrary(Integer movie) {
        movieList.get(movie).setCheckedOut(true);
    }

    public void returnMovieInLibrary(Integer movie) {
        movieList.get(movie).setCheckedOut(false);
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public String printBookList() {
        String returnStr = "";
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getCheckedOut() == false) {
                returnStr = returnStr + bookList.get(i).toString();
                returnStr = returnStr + "\n";
            }
        }
        return returnStr;
    }

    public String printMovieList() {
        String returnStr = "";
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getCheckedOut() == false) {
                returnStr = returnStr + movieList.get(i).toString();
                returnStr = returnStr + "\n";
            }
        }
        return returnStr;
    }
}
