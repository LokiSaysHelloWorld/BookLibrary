package com.loki.springRestAPI.demo.restControllers;

import com.loki.springRestAPI.demo.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    public boolean bookExists(String id){
        Optional<Library> libraryOptional = libraryRepository.findById(id);
        if(libraryOptional.isPresent())
            return true;
        return false;
    }

    public String buildId(String isbn, int aisle){
        String id = isbn + aisle;
        return id;
    }
}
