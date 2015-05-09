package com.example.vaadindemo.domain;


public class Book {
    
    private String title;
    private String author;
    private String ISBN;
    private int pagesNumber;
    private double price;

    public Book() {
        title = "brak danych";
        author = "brak danych";
        ISBN = "brak danych";
        pagesNumber = 0;
        price = 0;
    }
    
    public Book(String title, String author, String ISBN, int pagesNumber, double price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.pagesNumber = pagesNumber;
        this.price = price;
    }

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
    
     public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + author + ", pagesNumber=" 
                + pagesNumber + ", ISBN=" + ISBN + ", price=" + price + '}';
    }
    
    
    
    
}
