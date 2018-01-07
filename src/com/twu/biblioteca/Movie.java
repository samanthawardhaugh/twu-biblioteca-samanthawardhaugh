package com.twu.biblioteca;

public class Movie extends LIbrary_Item {
    public Integer rating;
    public String director;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        String returnStr = title + " (" + year.toString() + ") - " + rating + "/10";
        return returnStr;
    }
}
