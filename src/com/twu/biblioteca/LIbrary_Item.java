package com.twu.biblioteca;

public class LIbrary_Item {
    public String title;
    public Integer year;
    public Boolean checkedOut = false;

    public Boolean getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(Boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        String returnStr = title + "(" + year.toString() + ")";
        return returnStr;
    }
}
