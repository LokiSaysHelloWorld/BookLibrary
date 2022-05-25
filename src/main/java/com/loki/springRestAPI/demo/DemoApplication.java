package com.loki.springRestAPI.demo;

import com.loki.springRestAPI.demo.repository.LibraryRepository;
import com.loki.springRestAPI.demo.restControllers.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
// http://localhost:8080/addBook
//JSON body, POST

@SpringBootApplication
public class DemoApplication {

//	@Autowired
//	LibraryRepository libraryRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Override
//	public void run(String[] args){
//		Library library = libraryRepository.findById("fdsefr343").get();
//		System.out.println(library.getAuthor());
//
//		Library en = new Library();
//		en.setAisle(133);
//		en.setAuthor("jaggu");
//		en.setIsbn("accb");
//		en.setBook_name("webSpring");
//		en.setId("accb133");
//
//
//		libraryRepository.save(en);
//
//		System.out.println("saved new");
//
//		List<Library> allRecords = libraryRepository.findAll();
//		for(Library l : allRecords)
//			System.out.println(l.getId());

	//}

}
