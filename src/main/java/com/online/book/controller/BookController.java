package com.online.book.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.book.entity.Book;
import com.online.book.repository.BookRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	Book book;
	
	private byte[] bytes;
	
	//get All the books
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBookDetails(){
	 return new ResponseEntity<> (bookRepository.findAll(), HttpStatus.OK);
	}
	
	//Get book by book id
	@GetMapping("/books/book/{id}")
	public Optional<Book> getBookById(@PathVariable long id) {
	  return bookRepository.findById(id);
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}
	
	//Update Book
	@PutMapping("/addbook/{id}")
	public Book updateBook(@PathVariable(value="id") long id,@RequestBody Book updateBook){
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
	@PostMapping("/addbook")
	public void addNewBook(@RequestBody Book Newbook) {
		//Newbook.setBook_id(sequenceGeneratorService.generateSequence(book.SEQUENCE_NAME));
		Newbook.setBookimage(this.bytes);
		//return new ResponseEntity<> (bookRepository.save(Newbook), HttpStatus.CREATED);
		bookRepository.save(Newbook);
		this.bytes = null;
	}
	
	@DeleteMapping("/removeBook/{id}")
	public void removeBookById(@PathVariable long id) {
		
		bookRepository.deleteById(id);
		
		//return ResponseEntity.noContent();
	}
	
	
}
