package com.loki.springRestAPI.demo.repository;

import com.loki.springRestAPI.demo.restControllers.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepositoryCustom{
    List<Library> findAllByAuthor(String author);
}
