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
import com.online.book.entity.Wishlist;
import com.online.book.repository.CartRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AddToCartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	BookController bookrepo;

	@PostMapping("/addToCart/{id}")
	public AddToCart addBookToCart(@PathVariable(value="id") long id) {
		
		Optional<Book> book= bookrepo.getBookById(id);
		System.out.println("book details: "+ book.get());
		AddToCart addToCart= new AddToCart();
		book.ifPresent(b -> {
			addToCart.setCart_bookname(book.get().getBook_title());
			addToCart.setCart_bookauthor(book.get().getBook_author());
			addToCart.setCart_bookprice(book.get().getBook_price());
			addToCart.setCart_bookimage(book.get().getBookimage());
		});
		//wishlist.setBook(book.get());
		
		
		//int sub_total = cartBooks.stream().mapToInt(e->e.getCart_bookprice()).sum();
		
		
		return cartRepository.save(addToCart);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<List<AddToCart>> getAllWishlistBooks(){
		
		int sub_total = getSubTotal();
		
		List<AddToCart> cartBooks = cartRepository.findAll();
		
		for(AddToCart cart: cartBooks) {
			cart.setSub_total(sub_total);
		}
		
	 return new ResponseEntity<> (cartRepository.findAll(), HttpStatus.OK);
	}
	
	public int getSubTotal() {
		int sum=0;
		
		List<AddToCart> cartBooks = cartRepository.findAll();
         for(AddToCart cart: cartBooks) {
			
			int price = cart.getCart_bookprice();
			
			
			sum = sum+price;
			 
		}
      
         return sum;
	}
	
	@DeleteMapping("/removeFromCart/{id}")
	public void removeBookFromWishlist(@PathVariable long id) {
		cartRepository.deleteById(id);
	}
}
