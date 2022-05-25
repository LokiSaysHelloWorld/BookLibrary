package com.loki.springRestAPI.demo.repository;

import com.loki.springRestAPI.demo.restControllers.Library;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom{

    @Autowired
    LibraryRepository libraryRepository;
    @Override
    public List<Library> findAllByAuthor(String author) {
        List<Library> booksWithAuthor = new ArrayList<>();
        List<Library> allBooksByAuthor = libraryRepository.findAll();
        for(Library l : allBooksByAuthor){
            if(l.getAuthor().equalsIgnoreCase(author)){
                booksWithAuthor.add(l);
            }
        }

        return booksWithAuthor;
    }
}
