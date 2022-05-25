package com.loki.springRestAPI.demo.restControllers;

public class GetBookResponse {
    String msg;
    Library library;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
