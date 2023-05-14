package com.example.restapis;

public class BookAuthor {

    private Author author;
    private Book book;

    // use this as RequestBody but you can't use two @RequestBody!!!
    // you don't want to show it to outside world (like your model)
}
