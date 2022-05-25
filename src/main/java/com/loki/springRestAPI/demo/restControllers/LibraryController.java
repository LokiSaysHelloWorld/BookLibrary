package com.loki.springRestAPI.demo.restControllers;

import com.loki.springRestAPI.demo.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    LibraryService libraryService;

    @PostMapping("addBook")
    public ResponseEntity<BookAddResponse> addBookLibrary(@RequestBody Library library){
        BookAddResponse bookAddResponse = new BookAddResponse();
        ResponseEntity<BookAddResponse> responseEntity;
        String id = libraryService.buildId(library.getIsbn(), library.getAisle());
        if(!libraryService.bookExists(id)) {
            library.setId(id);
            libraryRepository.save(library);
            bookAddResponse.setMsg("book added");
            bookAddResponse.setId(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("bookID", id);
            responseEntity = new ResponseEntity<>(bookAddResponse, headers, HttpStatus.CREATED);
        }
        else{
            bookAddResponse.setMsg("book already exists, so not adding");
            bookAddResponse.setId(id);
            responseEntity = new ResponseEntity<>(bookAddResponse, HttpStatus.ACCEPTED);
        }
        return responseEntity;
    }

    @GetMapping("getBook/{id}")
    public ResponseEntity<GetBookResponse> getBookLibrary(@PathVariable(value="id") String id){
        ResponseEntity<GetBookResponse> responseEntity;
        GetBookResponse getBookResponse = new GetBookResponse();
        try {
            Library library = libraryRepository.findById(id).get();
            getBookResponse.setMsg("book found");
            getBookResponse.setLibrary(library);
            responseEntity = new ResponseEntity<>(getBookResponse, HttpStatus.FOUND);
            return responseEntity;
        }catch(Exception e){
            getBookResponse.setMsg("book not found");
            responseEntity = new ResponseEntity<>(getBookResponse, HttpStatus.NOT_FOUND);
            return responseEntity;
        }
    }

    @GetMapping("getBooks/author/{author}")
    public ResponseEntity<BooksListResponse> getBooksByAuthor(@PathVariable(name="author") String author){
        List<Library> books = libraryRepository.findAllByAuthor(author);
        ResponseEntity<BooksListResponse> responseEntity;
        BooksListResponse booksListResponse = new BooksListResponse();
        if(!books.isEmpty()){
            booksListResponse.setMsg("found books");
            booksListResponse.setBooks(books);
            responseEntity = new ResponseEntity<>(booksListResponse, HttpStatus.FOUND);
        }
        else {
            booksListResponse.setMsg("no books found for this author");
            responseEntity = new ResponseEntity<>(booksListResponse, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping("updateBook")
    public void updateBookById(@RequestBody Library library, @RequestParam(name="id") String id){
        if(libraryService.bookExists(id)) {
            Library lib = libraryRepository.findById(id).get();
            lib.setBook_name(library.getBook_name());
            lib.setAisle(library.getAisle());
            lib.setIsbn(library.getIsbn());
            lib.setAuthor(library.getAuthor());
            libraryRepository.save(lib);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("deleteBook")
    public void deleteBookById(@RequestParam(name="id") String id){
        if(libraryService.bookExists(id)) {
            Library lib = libraryRepository.findById(id).get();
            libraryRepository.delete(lib);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
