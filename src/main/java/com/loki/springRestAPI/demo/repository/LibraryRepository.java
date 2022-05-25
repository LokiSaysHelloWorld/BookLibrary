package com.loki.springRestAPI.demo.repository;

import com.loki.springRestAPI.demo.restControllers.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {
}
