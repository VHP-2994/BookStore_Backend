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
import com.online.book.repository.CartRepository;
import com.online.book.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AddToCartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	BookController bookrepo;
	
	@Autowired
	UserRepository userRepository;

	@PostMapping("/addToCart/{id}/{userId}")
	public Optional<AddToCart> addBookToCart(@PathVariable(value="id") long id,@PathVariable(value="userId") int userId) {
		
		Optional<Book> book= bookrepo.getBookById(id);
		System.out.println("book details: "+ book.get());
		AddToCart addToCart= new AddToCart();
		book.ifPresent(b -> {
			addToCart.setCart_bookname(book.get().getBook_title());
			addToCart.setCart_bookauthor(book.get().getBook_author());
			addToCart.setCart_bookprice(book.get().getBook_price());
			addToCart.setCart_bookimage(book.get().getBookimage());
		});
		
		Optional<User> userr = userRepository.findById(userId);
    	User signInUser = userr.get();
    	System.out.println(signInUser);
    	return userRepository.findById(userId).map(user -> {
			 addToCart.setUser(user);
	            return cartRepository.save(addToCart);
	        });
		
		//return cartRepository.save(addToCart);
	}
	
	@GetMapping("/cart/{userId}")
	public List<AddToCart> getAllCartBooksByUserID(@PathVariable int userId){
		
		//int sub_total = getSubTotal();
		int sum = 0;
		List<AddToCart> cartBooks =  cartRepository.findByUserId(userId);
		
		for(AddToCart cart: cartBooks) {
			int price = cart.getCart_bookprice();
			sum = sum + price;
			cart.setSub_total(sum);
		}
		
	 return cartRepository.findByUserId(userId);
	}
	
	/*
	 * public int getSubTotal() { int sum=0;
	 * 
	 * List<AddToCart> cartBooks = cartRepository.findByUserId(userId);
	 * for(AddToCart cart: cartBooks) {
	 * 
	 * int price = cart.getCart_bookprice();
	 * 
	 * 
	 * sum = sum+price;
	 * 
	 * }
	 * 
	 * return sum; }
	 */
	
	@DeleteMapping("/removeFromCart/{id}")
	public void removeBookFromWishlist(@PathVariable long id) {
		cartRepository.deleteById(id);
	}
	
	/*
	 * @DeleteMapping("/removeFromCart/{id}") public void
	 * removeBookFromCartByUserId(@PathVariable int userId) { List<AddToCart>
	 * cartItemRemove = cartRepository.findByUserId(userId);
	 * cartRepository.deleteInBatch(cartItemRemove); }
	 */
	
	@GetMapping("/cartId/{id}")
	public Optional<AddToCart> getCartItemByCartId(@PathVariable long id) {
		return cartRepository.findById(id);
	}
}
