package com.loki.springRestAPI.demo;

import com.loki.springRestAPI.demo.repository.LibraryRepository;
import com.loki.springRestAPI.demo.restControllers.BookAddResponse;
import com.loki.springRestAPI.demo.restControllers.Library;
import com.loki.springRestAPI.demo.restControllers.LibraryController;
import com.loki.springRestAPI.demo.restControllers.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	LibraryController con;
	@MockBean
	LibraryService libraryService;

//	@MockBean
//	LibraryRepository libraryRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	void checkBuildId(){
//		String isbn = "japan";
//		int aisle = 34;
//		Assertions.assertEquals(libraryService.buildId(isbn, aisle), "japan34");
//	}

	@Test
	void testAddBookLibrary(){
		Library lib = buildLibrary();
		when(libraryService.buildId(lib.getIsbn(), lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.bookExists(lib.getId())).thenReturn(true);
		ResponseEntity response = con.addBookLibrary(buildLibrary());
		BookAddResponse responseBody = (BookAddResponse)response.getBody();
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("book already exists, so not adding",responseBody.getMsg());
		assertEquals("wssa23", responseBody.getId());
	}

	@Test
	void testAddBookLibraryBookExistsFalse(){
		Library lib = buildLibrary();
		when(libraryService.buildId(lib.getIsbn(), lib.getAisle())).thenReturn(lib.getId());
		when(libraryService.bookExists(lib.getId())).thenReturn(false);
		ResponseEntity response = con.addBookLibrary(buildLibrary());
		BookAddResponse responseBody = (BookAddResponse)response.getBody();
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("book added",responseBody.getMsg());
		assertEquals("wssa23", responseBody.getId());
	}

	Library buildLibrary(){
		Library library = new Library();
		library.setAuthor("harkishan");
		library.setBook_name("springmvc");
		library.setAisle(23);
		library.setIsbn("wssa");
		library.setId("wssa23");
		return library;
	}
}
