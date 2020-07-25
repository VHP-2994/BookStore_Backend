package com.online.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.online.book.entity.Book;
import com.online.book.repository.BookRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	Book book;
	
	//get All the books
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBookDetails(){
	 return new ResponseEntity<> (bookRepository.findAll(), HttpStatus.OK);
	}
	
	//Get book by book id
	@GetMapping("/book/{id}")
	public Optional<Book> getBookById(@PathVariable int id) {
	  return bookRepository.findById(id);
	}
	
	//Update Book
	@PutMapping("/book/{id}")
	public Book updateBook(@PathVariable(value="id") int id,@RequestBody Book updateBook){
		Optional<Book> bookResponse = bookRepository.findById(id);
		bookResponse.ifPresent(b -> {
			b.setBook_title(updateBook.getBook_title());
			b.setBook_author(updateBook.getBook_author());
			b.setBook_price(updateBook.getBook_price());
		});
		Book book = bookResponse.get();
		return bookRepository.save(book);
		
	}
	
	//Create new Book
	@PostMapping("/book")
	public ResponseEntity<Book> addNewBook(@RequestBody Book Newbook) {
	
		return new ResponseEntity<> (bookRepository.save(Newbook), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeBook/{id}")
	public void removeBookById(@PathVariable int id) {
		
		bookRepository.deleteById(id);
		
		//return ResponseEntity.noContent();
	}
	
	
}
