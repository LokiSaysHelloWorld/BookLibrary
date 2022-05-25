package com.loki.springRestAPI.demo.restControllers;

import java.util.List;

public class BooksListResponse {
    private String msg;
    private List<Library> books;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Library> getBooks() {
        return books;
    }

    public void setBooks(List<Library> books) {
        this.books = books;
    }
}
