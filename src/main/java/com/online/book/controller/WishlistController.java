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
import org.springframework.web.bind.annotation.RestController;

import com.online.book.entity.AddToCart;
import com.online.book.entity.Book;
import com.online.book.entity.User;
import com.online.book.entity.Wishlist;
import com.online.book.repository.UserRepository;
import com.online.book.repository.WishlistRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController {

	@Autowired
	WishlistRepository wishlistrepo;
	
	@Autowired
	BookController bookrepo;

	@Autowired
	Book book;
	
	@Autowired
	UserRepository userRepository;

	// Create new item in wishlist
	@PostMapping("/addToWishlist/{id}/{userId}")
	public Optional<Object> addBookToWishlist(@PathVariable(value="id") long id,@PathVariable(value="userId") int userId) {
		
		Optional<Book> book= bookrepo.getBookById(id);
		System.out.println("book details: "+ book.get());
		Wishlist wishlist= new Wishlist();
		book.ifPresent(b -> {
			wishlist.setWish_id(book.get().getBook_id());
			wishlist.setWish_bookname(book.get().getBook_title());
			wishlist.setWish_bookauthor(book.get().getBook_author());
			wishlist.setWish_bookprice(book.get().getBook_price());
			wishlist.setWish_bookimage(book.get().getBookimage());
		});
		//wishlist.setBook(book.get());
		//return wishlistrepo.save(wishlist);
		Optional<User> userr = userRepository.findById(userId);
    	User signInUser = userr.get();
    	System.out.println(signInUser);
    	return userRepository.findById(userId).map(user -> {
    		wishlist.setUser(user);
	            return wishlistrepo.save(wishlist);
	        });
	}
	
	@DeleteMapping("/removeWishlist/{id}")
	public void removeBookFromWishlist(@PathVariable long id) {
		wishlistrepo.deleteById(id);
	}
	
	@GetMapping("/wishlist/{userId}")
	public List<Wishlist> getAllWishlistBooks(@PathVariable(value="userId") int userId){
	 return wishlistrepo.findByUserId(userId);
	}
}
